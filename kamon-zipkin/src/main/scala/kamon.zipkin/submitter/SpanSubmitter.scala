/**
 * Copyright 2014 the Akka Tracing contributors. See AUTHORS for more details.
 * https://github.com/levkhomich/akka-tracing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kamon.zipkin.submitter

import java.net.{ ConnectException, NoRouteToHostException, SocketException }
import javax.xml.bind.DatatypeConverter
import collection.JavaConversions._

import akka.actor.ActorLogging
import akka.stream.actor.{ ActorPublisher, ActorSubscriber, ActorSubscriberMessage, RequestStrategy }
import org.apache.thrift.TApplicationException
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.{ TTransport, TTransportException }

import scala.collection.mutable
import scala.concurrent.duration.DurationInt
import scala.util.control.ControlThrowable
import scala.util.{ Success, Try }

import kamon.zipkin.thrift

private[submitter] object SpanSubmitter {
  private case object SendEnqueued
}

final case class SubmitSpans(spans: TraversableOnce[thrift.Span])

private[submitter] class SpanHolder() extends ActorPublisher[thrift.Span] {

  override def receive: Receive = {
    case SubmitSpans(submitSpans) ⇒
      submitSpans.foreach(span ⇒
        if (isActive && totalDemand > 0)
          onNext(span))
    case _ ⇒
  }

}

/**
 * Internal API
 */
private[submitter] class SpanSubmitter(transport: TTransport) extends ActorSubscriber with ActorLogging {

  import SpanSubmitter._

  // buffer containing log entries ready to be sent
  private[this] val logEntries = mutable.UnrolledBuffer[thrift.LogEntry]()

  // buffer watermarks
  private[this] val highWatermark = 2000
  private[this] val lowWatermark = 1000

  private[this] val protocolFactory = new TBinaryProtocol.Factory()
  private[this] val thriftBuffer = new TReusableTransport()

  private[this] val client = new thrift.Scribe.Client(new TBinaryProtocol(transport))

  protected def requestStrategy = new RequestStrategy {
    def requestDemand(remainingRequested: Int): Int = {
      val spanCount = logEntries.size + remainingRequested
      if (spanCount < lowWatermark)
        highWatermark - spanCount
      else
        0
    }
  }

  override def receive: Receive = {
    case ActorSubscriberMessage.OnNext(span: thrift.Span) ⇒
      logEntries.append(spanToLogEntry(span))
    case ActorSubscriberMessage.OnError(ex) ⇒
      flush()
    case ActorSubscriberMessage.OnComplete ⇒
      flush()
    case SendEnqueued ⇒
      send()
      scheduleNextBatch()
  }

  override def preStart(): Unit = {
    scheduleNextBatch()
  }

  override def postStop(): Unit = {
    flush()
    super.postStop()
  }

  private[this] def flush(): Unit = {
    log.info(s"Flushing ${logEntries.size} spans")
    send()
    if (transport.isOpen) {
      transport.close()
    }
  }

  private[this] def send(): Unit = {
    val sentWatermark = logEntries.size
    if (sentWatermark > 0) {
      Try {
        if (!transport.isOpen) {
          transport.open()
          TracingExtension(context.system).markCollectorAsAvailable()
          log.info("Successfully connected to Zipkin collector.")
        }
        client.Log(logEntries)
      } recover {
        case e ⇒
          handleSubmissionError(e)
          // reconnect next time
          transport.close()
          thrift.ResultCode.TRY_LATER
      } match {
        case Success(thrift.ResultCode.OK) ⇒
          logEntries.remove(0, sentWatermark)
        case _ ⇒
          log.warning(s"Zipkin collector unavailable. Failed to send $sentWatermark spans.")
      }
    }
  }

  private[this] def spanToLogEntry(spanInt: thrift.Span): thrift.LogEntry = {
    spanInt.write(protocolFactory.getProtocol(thriftBuffer))
    val thriftBytes = thriftBuffer.getArray().take(thriftBuffer.length)
    thriftBuffer.reset()
    val encodedSpan = DatatypeConverter.printBase64Binary(thriftBytes) + '\n'
    new thrift.LogEntry("zipkin", encodedSpan)
  }

  private[this] def handleSubmissionError(e: Throwable): Unit =
    e match {
      case te: TTransportException ⇒
        te.getCause match {
          case null ⇒
            log.error("Thrift transport error: " + te.getMessage)
          case e: ConnectException ⇒
            log.error("Can't connect to Zipkin: " + e.getMessage)
          case e: NoRouteToHostException ⇒
            log.error("No route to Zipkin: " + e.getMessage)
          case e: SocketException ⇒
            log.error("Socket error: " + e.getMessage)
          case t: Throwable ⇒
            log.error("Unknown transport error: " + TracingExtension.getStackTrace(t))
        }
        TracingExtension(context.system).markCollectorAsUnavailable()
      case t: TApplicationException ⇒
        log.error("Thrift client error: " + t.getMessage)
      case ct: ControlThrowable ⇒
        throw ct
      case t: Throwable ⇒
        log.error("Oh, look! We have an unknown error here: " + TracingExtension.getStackTrace(t))
    }

  private[this] def scheduleNextBatch(): Unit = {
    import context.dispatcher
    if (TracingExtension(context.system).isEnabled) {
      context.system.scheduler.scheduleOnce(2.seconds, self, SendEnqueued)
    } else {
      log.error("Trying to reconnect in 10 seconds")
      context.system.scheduler.scheduleOnce(10.seconds, self, SendEnqueued)
    }
  }

}

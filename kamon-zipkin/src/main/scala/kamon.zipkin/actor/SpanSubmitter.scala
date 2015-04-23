package kamon.zipkin.actor

import akka.actor.{ ActorLogging, Actor }
import com.github.levkhomich.akka.tracing.TracingExtensionImpl
import kamon.trace.{ TraceSettings, HierarchyConfig }
import kamon.util.NanoTimestamp
import kamon.zipkin.ZipkinConfig
import kamon.zipkin.models.{ Span, SpanBlock }

import scala.collection.mutable

/*
 * This actor submits spans to Zipkin using akka-tracing library @ https://github.com/levkhomich/akka-tracing
 */
class SpanSubmitter(traceSettings: TraceSettings, tracingExt: Option[TracingExtensionImpl] = None) extends Actor with ActorLogging {

  def receive: Actor.Receive = {
    case spanBlock: SpanBlock if tracingExt.isEmpty ⇒
      val spans = spanBlock.spans
      if (spanBlock.remote || spans.exists { case (_, span) ⇒ traceSettings.sampler.shouldReport(span.getNanoDuration) })
        sender() ! buildHierarchy(spans)
    case spanBlock: SpanBlock ⇒
      val spans = spanBlock.spans
      if (spanBlock.remote || spans.exists { case (_, span) ⇒ traceSettings.sampler.shouldReport(span.getNanoDuration) })
        tracingExt.get.submitSpans(buildHierarchy(spans).map(_._2.simpleSpan))
  }

  private def buildHierarchy(spans: mutable.Map[String, Span]): Map[String, Span] = {
    val traceInstance = mutable.Map.empty[String, String]

    // Merge spans which have the same instance
    spans.toSeq.sortBy(s ⇒ (s._2.trace.timestamp.nanos, s._2.trace.token)).foreach {
      case (token, span) ⇒
        val trace = span.trace
        val instance = trace.metadata.getOrElse(HierarchyConfig.spanUniqueClass, "")

        traceInstance.get(instance) match {
          case Some(instanceToken) ⇒ spans.get(instanceToken).foreach { realSpan ⇒
            realSpan.addToRecords(
              ZipkinConfig.segmentBegin + trace.metadata.getOrElse(ZipkinConfig.spanType, "unknown"),
              new NanoTimestamp(trace.timestamp.nanos))
            realSpan.addToRecords(
              ZipkinConfig.segmentEnd + trace.metadata.getOrElse(ZipkinConfig.spanType, "unknown"),
              new NanoTimestamp(trace.timestamp.nanos + trace.elapsedTime.nanos))
            realSpan.addSegments(trace.segments)

            spans += (instanceToken -> realSpan)
            spans -= token
          }
          case None ⇒ traceInstance += (instance -> token)
        }
    }

    // Edit parentToken of spans whose parent was merged
    spans.toSeq.sortBy(s ⇒ (s._2.trace.timestamp.nanos, s._2.trace.token)).foreach {
      case (token, span) ⇒
        span.trace.metadata.get(ZipkinConfig.parentClass).foreach { parentInstance ⇒
          traceInstance.get(parentInstance).foreach { parentToken ⇒
            spans += (token -> span.copy(parentSpanId = parentToken))
          }
        }
    }

    spans.toMap
  }

}
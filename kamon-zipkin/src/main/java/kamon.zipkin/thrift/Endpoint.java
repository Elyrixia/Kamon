/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package kamon.zipkin.thrift;

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import javax.annotation.Generated;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-4-27")
public class Endpoint implements org.apache.thrift.TBase<kamon.zipkin.thrift.Endpoint, kamon.zipkin.thrift.Endpoint._Fields>, java.io.Serializable, Cloneable, Comparable<kamon.zipkin.thrift.Endpoint> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Endpoint");

  private static final org.apache.thrift.protocol.TField IPV4_FIELD_DESC = new org.apache.thrift.protocol.TField("ipv4", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("port", org.apache.thrift.protocol.TType.I16, (short)2);
  private static final org.apache.thrift.protocol.TField SERVICE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("service_name", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new EndpointStandardSchemeFactory());
    schemes.put(TupleScheme.class, new EndpointTupleSchemeFactory());
  }

  public int ipv4; // required
  public short port; // required
  public String service_name; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    IPV4((short)1, "ipv4"),
    PORT((short)2, "port"),
    SERVICE_NAME((short)3, "service_name");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // IPV4
          return IPV4;
        case 2: // PORT
          return PORT;
        case 3: // SERVICE_NAME
          return SERVICE_NAME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __IPV4_ISSET_ID = 0;
  private static final int __PORT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.IPV4, new org.apache.thrift.meta_data.FieldMetaData("ipv4", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PORT, new org.apache.thrift.meta_data.FieldMetaData("port", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.SERVICE_NAME, new org.apache.thrift.meta_data.FieldMetaData("service_name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(kamon.zipkin.thrift.Endpoint.class, metaDataMap);
  }

  public Endpoint() {
  }

  public Endpoint(
    int ipv4,
    short port,
    String service_name)
  {
    this();
    this.ipv4 = ipv4;
    set_ipv4_isSet(true);
    this.port = port;
    set_port_isSet(true);
    this.service_name = service_name;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Endpoint(kamon.zipkin.thrift.Endpoint other) {
    __isset_bitfield = other.__isset_bitfield;
    this.ipv4 = other.ipv4;
    this.port = other.port;
    if (other.is_set_service_name()) {
      this.service_name = other.service_name;
    }
  }

  public kamon.zipkin.thrift.Endpoint deepCopy() {
    return new kamon.zipkin.thrift.Endpoint(this);
  }

  @Override
  public void clear() {
    set_ipv4_isSet(false);
    this.ipv4 = 0;
    set_port_isSet(false);
    this.port = 0;
    this.service_name = null;
  }

  public int get_ipv4() {
    return this.ipv4;
  }

  public kamon.zipkin.thrift.Endpoint set_ipv4(int ipv4) {
    this.ipv4 = ipv4;
    set_ipv4_isSet(true);
    return this;
  }

  public void unset_ipv4() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __IPV4_ISSET_ID);
  }

  /** Returns true if field ipv4 is set (has been assigned a value) and false otherwise */
  public boolean is_set_ipv4() {
    return EncodingUtils.testBit(__isset_bitfield, __IPV4_ISSET_ID);
  }

  public void set_ipv4_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __IPV4_ISSET_ID, value);
  }

  public short get_port() {
    return this.port;
  }

  public kamon.zipkin.thrift.Endpoint set_port(short port) {
    this.port = port;
    set_port_isSet(true);
    return this;
  }

  public void unset_port() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  /** Returns true if field port is set (has been assigned a value) and false otherwise */
  public boolean is_set_port() {
    return EncodingUtils.testBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  public void set_port_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PORT_ISSET_ID, value);
  }

  public String get_service_name() {
    return this.service_name;
  }

  public kamon.zipkin.thrift.Endpoint set_service_name(String service_name) {
    this.service_name = service_name;
    return this;
  }

  public void unset_service_name() {
    this.service_name = null;
  }

  /** Returns true if field service_name is set (has been assigned a value) and false otherwise */
  public boolean is_set_service_name() {
    return this.service_name != null;
  }

  public void set_service_name_isSet(boolean value) {
    if (!value) {
      this.service_name = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case IPV4:
      if (value == null) {
        unset_ipv4();
      } else {
        set_ipv4((Integer)value);
      }
      break;

    case PORT:
      if (value == null) {
        unset_port();
      } else {
        set_port((Short)value);
      }
      break;

    case SERVICE_NAME:
      if (value == null) {
        unset_service_name();
      } else {
        set_service_name((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case IPV4:
      return Integer.valueOf(get_ipv4());

    case PORT:
      return Short.valueOf(get_port());

    case SERVICE_NAME:
      return get_service_name();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case IPV4:
      return is_set_ipv4();
    case PORT:
      return is_set_port();
    case SERVICE_NAME:
      return is_set_service_name();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof kamon.zipkin.thrift.Endpoint)
      return this.equals((kamon.zipkin.thrift.Endpoint)that);
    return false;
  }

  public boolean equals(kamon.zipkin.thrift.Endpoint that) {
    if (that == null)
      return false;

    boolean this_present_ipv4 = true;
    boolean that_present_ipv4 = true;
    if (this_present_ipv4 || that_present_ipv4) {
      if (!(this_present_ipv4 && that_present_ipv4))
        return false;
      if (this.ipv4 != that.ipv4)
        return false;
    }

    boolean this_present_port = true;
    boolean that_present_port = true;
    if (this_present_port || that_present_port) {
      if (!(this_present_port && that_present_port))
        return false;
      if (this.port != that.port)
        return false;
    }

    boolean this_present_service_name = true && this.is_set_service_name();
    boolean that_present_service_name = true && that.is_set_service_name();
    if (this_present_service_name || that_present_service_name) {
      if (!(this_present_service_name && that_present_service_name))
        return false;
      if (!this.service_name.equals(that.service_name))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_ipv4 = true;
    list.add(present_ipv4);
    if (present_ipv4)
      list.add(ipv4);

    boolean present_port = true;
    list.add(present_port);
    if (present_port)
      list.add(port);

    boolean present_service_name = true && (is_set_service_name());
    list.add(present_service_name);
    if (present_service_name)
      list.add(service_name);

    return list.hashCode();
  }

  @Override
  public int compareTo(kamon.zipkin.thrift.Endpoint other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(is_set_ipv4()).compareTo(other.is_set_ipv4());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_ipv4()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ipv4, other.ipv4);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_port()).compareTo(other.is_set_port());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_port()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.port, other.port);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_service_name()).compareTo(other.is_set_service_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_service_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.service_name, other.service_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Endpoint(");
    boolean first = true;

    sb.append("ipv4:");
    sb.append(this.ipv4);
    first = false;
    if (!first) sb.append(", ");
    sb.append("port:");
    sb.append(this.port);
    first = false;
    if (!first) sb.append(", ");
    sb.append("service_name:");
    if (this.service_name == null) {
      sb.append("null");
    } else {
      sb.append(this.service_name);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class EndpointStandardSchemeFactory implements SchemeFactory {
    public EndpointStandardScheme getScheme() {
      return new EndpointStandardScheme();
    }
  }

  private static class EndpointStandardScheme extends StandardScheme<kamon.zipkin.thrift.Endpoint> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, kamon.zipkin.thrift.Endpoint struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
          break;
        }
        switch (schemeField.id) {
          case 1: // IPV4
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.ipv4 = iprot.readI32();
              struct.set_ipv4_isSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.port = iprot.readI16();
              struct.set_port_isSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SERVICE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.service_name = iprot.readString();
              struct.set_service_name_isSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, kamon.zipkin.thrift.Endpoint struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(IPV4_FIELD_DESC);
      oprot.writeI32(struct.ipv4);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PORT_FIELD_DESC);
      oprot.writeI16(struct.port);
      oprot.writeFieldEnd();
      if (struct.service_name != null) {
        oprot.writeFieldBegin(SERVICE_NAME_FIELD_DESC);
        oprot.writeString(struct.service_name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class EndpointTupleSchemeFactory implements SchemeFactory {
    public EndpointTupleScheme getScheme() {
      return new EndpointTupleScheme();
    }
  }

  private static class EndpointTupleScheme extends TupleScheme<kamon.zipkin.thrift.Endpoint> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, kamon.zipkin.thrift.Endpoint struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.is_set_ipv4()) {
        optionals.set(0);
      }
      if (struct.is_set_port()) {
        optionals.set(1);
      }
      if (struct.is_set_service_name()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.is_set_ipv4()) {
        oprot.writeI32(struct.ipv4);
      }
      if (struct.is_set_port()) {
        oprot.writeI16(struct.port);
      }
      if (struct.is_set_service_name()) {
        oprot.writeString(struct.service_name);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, kamon.zipkin.thrift.Endpoint struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.ipv4 = iprot.readI32();
        struct.set_ipv4_isSet(true);
      }
      if (incoming.get(1)) {
        struct.port = iprot.readI16();
        struct.set_port_isSet(true);
      }
      if (incoming.get(2)) {
        struct.service_name = iprot.readString();
        struct.set_service_name_isSet(true);
      }
    }
  }

}


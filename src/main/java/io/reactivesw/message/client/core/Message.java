package io.reactivesw.message.client.core;

import java.io.Serializable;
import java.util.UUID;

/**
 * Message for publish or receive.
 */
public class Message implements Serializable {

  /**
   * unique message id.
   */
  public String id;

  /**
   * sequence number used to detect the order of the message.
   */
  public String sequenceNumber;

  /**
   * message data.
   */
  public Object payload;

  public static Message of(Object payload) {
    Message msg = new Message();
    msg.id = UUID.randomUUID().toString();
    msg.payload = payload;
    return msg;
  }

  public static Message of(String id, Object payload) {
    Message msg = new Message();
    msg.id = id;
    msg.payload = payload;
    return msg;
  }

  public static Message of(String id, String sequenceNumber, Object payload) {
    Message msg = new Message();
    msg.id = id;
    msg.sequenceNumber = sequenceNumber;
    msg.payload = payload;
    return msg;
  }

}

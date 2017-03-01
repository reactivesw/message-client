package io.reactivesw.message.client.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * Message for publish or receive.
 * All messages in our system should be an json.
 */
@Getter
@Setter
public class Message implements Serializable {

  /**
   * unique message id.
   */
  private String id;

  /**
   * sequence number used to detect the order of the message.
   */
  private String sequenceNumber;

  /**
   * message data.
   */
  private Serializable payload;

  public static Message of(Serializable payload) {
    Message msg = new Message();
    msg.id = UUID.randomUUID().toString();
    msg.payload = payload;
    return msg;
  }

  public static Message of(String id, Serializable payload) {
    Message msg = new Message();
    msg.id = id;
    msg.payload = payload;
    return msg;
  }

  public static Message of(String id, String sequenceNumber, Serializable payload) {
    Message msg = new Message();
    msg.id = id;
    msg.sequenceNumber = sequenceNumber;
    msg.payload = payload;
    return msg;
  }

}

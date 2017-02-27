package io.reactivesw.message.client.core;

/**
 * Message for publish or receive.
 */
public class Message<T> {

  /**
   * unique message id.
   */
  public String id;

  /**
   * message data.
   */
  public T payload;
}

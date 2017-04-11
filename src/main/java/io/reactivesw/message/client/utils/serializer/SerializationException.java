package io.reactivesw.message.client.utils.serializer;

/**
 * Serialization exception.
 */
public class SerializationException extends RuntimeException {

  /**
   * default constuctor.
   */
  public SerializationException() {
    super();
  }

  /**
   * constructor with detail message.
   *
   * @param msg message
   */
  public SerializationException(String msg) {
    super(msg);
  }

  /**
   * constructor with detail message and source exception.
   *
   * @param msg message
   * @param ex  source exception
   */
  public SerializationException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

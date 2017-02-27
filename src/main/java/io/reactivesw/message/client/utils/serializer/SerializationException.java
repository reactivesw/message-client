package io.reactivesw.message.client.utils.serializer;

/**
 * Serialization exception.
 */
public class SerializationException extends RuntimeException {

  public SerializationException() {
    super();
  }

  public SerializationException(String msg) {
    super(msg);
  }

  public SerializationException(String msg, Throwable e) {
    super(msg, e);
  }
}

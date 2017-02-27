package io.reactivesw.message.client.producer;

/**
 * producer creation exception.
 */
public class ProducerCreateException extends RuntimeException {
  public ProducerCreateException() {
    super();
  }

  public ProducerCreateException(String msg) {
    super(msg);
  }

  public ProducerCreateException(String msg, Throwable e) {
    super(msg, e);
  }
}

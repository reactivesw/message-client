package io.reactivesw.message.client.consumer;

/**
 * consumer creation exception.
 * create consumer failed exception.
 */
public class ConsumerCreateException extends RuntimeException {

  /**
   * default constructor.
   */
  public ConsumerCreateException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg detail message
   */
  public ConsumerCreateException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source exception.
   *
   * @param msg detail message
   * @param ex   source exception
   */
  public ConsumerCreateException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

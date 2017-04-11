package io.reactivesw.message.client.producer;

/**
 * producer creation exception.
 * create producer failed exception.
 */
public class ProducerCreateException extends RuntimeException {

  /**
   * aotu generated serial version id.
   */
  private static final long serialVersionUID = 2644742122956501881L;

  /**
   * default constructor.
   */
  public ProducerCreateException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg detail message
   */
  public ProducerCreateException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source exception.
   *
   * @param msg detail message
   * @param ex  source exception
   */
  public ProducerCreateException(String msg, Throwable ex) {
    super(msg, ex);
  }
}

package io.reactivesw.message.client.producer;

import io.reactivesw.message.client.core.Message;

import java.util.concurrent.Future;

/**
 * message producer.
 */
public interface Producer {

  /**
   * publish.
   *
   * @param msg message
   * @return future
   */
  Future<String> publish(Message msg);

  /**
   * publish.
   *
   * @param msg      message
   * @param callback callback
   */
  void publish(Message msg, Callback callback);

  /**
   * flush.
   */
  void flush();

  /**
   * close.
   *
   * @throws Exception exception
   */
  void close() throws Exception;
}

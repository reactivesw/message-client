package io.reactivesw.message.client.producer;

import io.reactivesw.message.client.core.Message;

import java.util.concurrent.Future;

/**
 * message producer.
 */
public interface Producer {

  Future<String> send(Message msg);

  Future<String> send(Message msg, Callback callback);

  void flush();

  void close() throws Exception;
}

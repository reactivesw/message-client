package io.reactivesw.message.client.consumer;

/**
 * Message consumer.
 * Create listener,
 */
public interface Consumer<T> {

  void addProcessor(MessageProcessor processor);

}

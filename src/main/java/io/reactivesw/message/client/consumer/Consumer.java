package io.reactivesw.message.client.consumer;

import io.reactivesw.message.client.core.Message;

import java.util.List;

/**
 * Message consumer.
 * Create listener,
 */
public interface Consumer {

  /**
   * add processor.
   * this can only be used when use 'push' not 'pull'
   *
   * @param processor
   */
  void addProcessor(MessageProcessor processor);

  /**
   * pull messages.
   * this will return immediately, and will return empty list if there is no available messages.
   *
   * @param maxMessages the max number of messages.
   * @return a list of message.
   */
  List<Message> pullMessages(int maxMessages);

  /**
   * pull messages.
   *
   * @return a list of message.
   */
  List<Message> pullMessages(boolean returnImmediately, int maxMessages);
}

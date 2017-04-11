package io.reactivesw.message.client.consumer;

import io.reactivesw.message.client.core.Message;

/**
 * Message processor.
 */
public interface MessageProcessor {

  /**
   * process the message.
   *
   * @param message message
   */
  void process(Message message);
}

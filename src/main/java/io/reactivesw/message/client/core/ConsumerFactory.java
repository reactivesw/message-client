package io.reactivesw.message.client.core;

import io.reactivesw.message.client.consumer.Consumer;

/**
 * The strategy to produce a {@link Consumer} instance(s).
 */
public interface ConsumerFactory {

  Consumer createConsumer() throws Exception;
}

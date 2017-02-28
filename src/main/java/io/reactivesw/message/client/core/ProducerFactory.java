package io.reactivesw.message.client.core;

import io.reactivesw.message.client.producer.Producer;

/**
 * The strategy to produce a {@link Producer} instance(s).
 */
public interface ProducerFactory {

  Producer createProducer() throws Exception;

  Producer createProducer(String topicName) throws Exception;
}

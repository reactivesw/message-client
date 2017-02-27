package io.reactivesw.message.client.core;

import io.reactivesw.message.client.producer.Producer;

/**
 * The strategy to produce a {@link Producer} instance(s).
 *
 * @param <K> the key type.
 * @param <V> the value type.
 */
public interface ProducerFactory<K, V> {

  Producer<K, V> createProducer();

}

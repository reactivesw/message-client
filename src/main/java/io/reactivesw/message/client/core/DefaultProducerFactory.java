package io.reactivesw.message.client.core;

import io.reactivesw.message.client.google.GoogleProducer;
import io.reactivesw.message.client.producer.Producer;

import java.io.IOException;

/**
 * The strategy to produce a {@link Producer} instance(s).
 */
public final class DefaultProducerFactory {

  /**
   * default private constructor.
   */
  private DefaultProducerFactory() {
  }

  /**
   * create google producer.
   *
   * @return Producer.
   * @throws IOException exception
   */
  public static Producer createGoogleProducer(String projectId, String topicName) throws
      IOException {
    return new GoogleProducer(projectId, topicName);
  }
}

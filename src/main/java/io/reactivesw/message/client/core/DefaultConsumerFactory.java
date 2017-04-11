package io.reactivesw.message.client.core;

import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.consumer.ConsumerCreateException;
import io.reactivesw.message.client.google.GoogleConsumer;

/**
 * Default consumer factory.
 */
public final class DefaultConsumerFactory {

  /**
   * default constructor.
   */
  private DefaultConsumerFactory() {
  }

  /**
   * create google pub/sub consumer.
   *
   * @param projectId      String
   * @param subscriptionId string
   * @return consumer
   * @throws ConsumerCreateException exception
   */
  public static Consumer createGoogleConsumer(String projectId, String subscriptionId) throws
      ConsumerCreateException {
    return new GoogleConsumer(projectId, subscriptionId);
  }
}

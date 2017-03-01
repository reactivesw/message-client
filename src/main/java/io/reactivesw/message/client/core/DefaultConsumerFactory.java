package io.reactivesw.message.client.core;

import io.reactivesw.message.client.config.MessageBinderConfigurations;
import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.google.GoogleConsumer;

import java.io.IOException;

/**
 * Default consumer factory.
 */
public class DefaultConsumerFactory implements ConsumerFactory {

  /**
   * message binder configs for broker.
   */
  MessageBinderConfigurations mbConfig;

  public DefaultConsumerFactory(MessageBinderConfigurations mbConfig) {
    this.mbConfig = mbConfig;
  }

  @Override
  public Consumer createConsumer() throws Exception {
    String brokerType = this.mbConfig.getBrokerType();
    switch (brokerType) {
      case "google":
        return createGoogleConsumer();
      default:
        return null;
    }
  }

  public Consumer createGoogleConsumer() throws IOException {
    String projectId = mbConfig.getGoogleProjectId();
    String subscriptionString = mbConfig.getSubscription();

    return new GoogleConsumer(projectId, subscriptionString);
  }
}

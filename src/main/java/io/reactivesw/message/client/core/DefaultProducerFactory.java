package io.reactivesw.message.client.core;

import io.reactivesw.message.client.config.MessageBinderConfigurations;
import io.reactivesw.message.client.google.GoogleProducer;
import io.reactivesw.message.client.producer.Producer;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * The strategy to produce a {@link Producer} instance(s).
 */
public class DefaultProducerFactory implements ProducerFactory {

  /**
   * message binder configs for broker.
   */
  MessageBinderConfigurations mbConfig;

  public DefaultProducerFactory(MessageBinderConfigurations mbConfig) {
    this.mbConfig = mbConfig;
  }

  @Override
  public Producer createProducer() throws Exception {
    String brokerType = this.mbConfig.getBrokerType();
    switch (brokerType) {
      case "google":
        return createGoogleProducer();
      default:
        return null;
    }
  }

  @Override
  public Producer createProducer(String topicName) {
    return null;
  }

  private Producer createGoogleProducer() throws IOException {
    String projectId = mbConfig.getGoogleProjectId();
    Assert.notNull(projectId);

    String topicName = mbConfig.getTopic();

    GoogleProducer producer;
    if (topicName != null) {
      producer = new GoogleProducer(projectId, topicName);
    } else {
      producer = new GoogleProducer(projectId);
    }
    return producer;
  }
}

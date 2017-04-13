package io.reactivesw.message.client.google;

import com.google.cloud.pubsub.spi.v1.SubscriptionAdminClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PullResponse;
import com.google.pubsub.v1.ReceivedMessage;
import com.google.pubsub.v1.SubscriptionName;
import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.consumer.ConsumerCreateException;
import io.reactivesw.message.client.consumer.MessageProcessor;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.utils.serializer.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Google Consumer client.
 */
public class GoogleConsumer implements Consumer {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(GoogleConsumer.class);

  /**
   * subscription name.
   */
  private transient SubscriptionName subscriptionName;

  /**
   * subscription admin client.
   */
  private transient SubscriptionAdminClient client;

  /**
   * json deserializer.
   */
  private transient JsonDeserializer<Message> jsonDeserializer = new JsonDeserializer<>(Message
      .class);

  /**
   * constructor.
   *
   * @param projectId      project id
   * @param subscriptionId subscription id
   * @throws IOException exception
   */
  public GoogleConsumer(String projectId, String subscriptionId)
      throws ConsumerCreateException {
    Assert.notNull(projectId, "google pub/sub projectId is null.");
    Assert.notNull(subscriptionId, "google pub/sub subscriptionId is null.");
    LOG.debug("projectId: {}, subscriptionId: {}.", projectId,
        subscriptionId);
    try {
      client = SubscriptionAdminClient.create();

      subscriptionName = SubscriptionName.create(projectId, subscriptionId);

    } catch (IOException ex) {
      LOG.debug("create consumer failed. projectId: {}, subscriptionId: {}.",
          projectId, subscriptionId, ex);
      throw new ConsumerCreateException("consumer create failed.");
    }

  }

  /**
   * add processor.
   *
   * @param processor processor
   */
  @Override
  public void addProcessor(MessageProcessor processor) {
    //TODO to be finished.
  }

  /**
   * pull message from broker. this function will return immediately.
   *
   * @param maxMessages the max number of messages
   * @return list of message.
   */
  @Override
  public List<Message> pullMessages(int maxMessages) {
    //TODO replace this to finish.
    PullResponse response = client.pull(subscriptionName, true, maxMessages);
    List<ReceivedMessage> messages = response.getReceivedMessagesList();

    return messages.stream().map(
        receivedMessage -> {
          ByteString data = receivedMessage.getMessage().getData();
          Message message = jsonDeserializer.deserialize(data.toString());
          message.setExternalId(receivedMessage.getAckId());
          return message;
        }
    ).collect(Collectors.toList());
  }

  /**
   * pull messages from broker.
   * if returnImmediately set false, then this function will wait util there is at least one
   * available message.
   *
   * @param returnImmediately if this function should return immediately
   * @param maxMessages       the max number of messages
   * @return list of messages
   */
  @Override
  public List<Message> pullMessages(boolean returnImmediately, int maxMessages) {
    PullResponse response = client.pull(subscriptionName, returnImmediately, maxMessages);
    List<ReceivedMessage> messages = response.getReceivedMessagesList();

    return messages.stream().map(
        receivedMessage -> {
          ByteString data = receivedMessage.getMessage().getData();
          Message message = jsonDeserializer.deserialize(data.toString());
          message.setExternalId(receivedMessage.getAckId());
          return message;
        }
    ).collect(Collectors.toList());
  }
}

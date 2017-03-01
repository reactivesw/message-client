package io.reactivesw.message.client.google;

import com.google.cloud.pubsub.spi.v1.Subscriber;
import com.google.cloud.pubsub.spi.v1.SubscriberClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PullResponse;
import com.google.pubsub.v1.ReceivedMessage;
import com.google.pubsub.v1.SubscriptionName;
import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.consumer.MessageProcessor;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.utils.serializer.JsonDeserializer;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Google Consumer client.
 */
public class GoogleConsumer implements Consumer {

  Subscriber subscriber;

  SubscriptionName subscriptionName;

  SubscriberClient client;

  JsonDeserializer<Message> jsonDeserializer = new JsonDeserializer<>(Message.class);

  /**
   * message processor executor.
   */
  GoogleMessageProcessor messageProcessor;

  public GoogleConsumer(String projectId, String subscriptionString)
      throws IOException {
    Assert.notNull(projectId);
    Assert.notNull(subscriptionString);

    client = SubscriberClient.create();
    this.subscriptionName = SubscriptionName.newBuilder()
        .setProject(projectId)
        .setSubscription(subscriptionString)
        .build();
  }

  @Override
  public void addProcessor(MessageProcessor processor) {
    //TODO to be finished.
  }

  @Override
  public List<Message> pullMessages(int maxMessages) {
    PullResponse response = client.pull(subscriptionName, true, maxMessages);
    List<ReceivedMessage> messages = response.getReceivedMessagesList();

    return messages.stream().map(
        receivedMessage -> {
          ByteString data = messages.get(0).getMessage().getData();
          return jsonDeserializer.deserialize(data.toByteArray());
        }
    ).collect(Collectors.toList());
  }

  @Override
  public List<Message> pullMessages(boolean returnImmediately, int maxMessages) {
    PullResponse response = client.pull(subscriptionName, returnImmediately, maxMessages);
    List<ReceivedMessage> messages = response.getReceivedMessagesList();

    return messages.stream().map(
        receivedMessage -> {
          ByteString data = messages.get(0).getMessage().getData();
          return jsonDeserializer.deserialize(data.toByteArray());
        }
    ).collect(Collectors.toList());
  }
}

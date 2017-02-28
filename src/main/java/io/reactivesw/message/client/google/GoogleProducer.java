package io.reactivesw.message.client.google;

import com.google.cloud.pubsub.spi.v1.Publisher;
import com.google.cloud.pubsub.spi.v1.PublisherClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.producer.Callback;
import io.reactivesw.message.client.producer.Producer;
import io.reactivesw.message.client.utils.serializer.JsonSerializer;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Google pub/sub producer client.
 */
public class GoogleProducer implements Producer {

  /**
   * google publisher.
   */
  Publisher publisher;

  PublisherClient publisherClient;

  JsonSerializer jsonSerializer = new JsonSerializer();

  private String projectId = "google-project-id";
  private String topicNameString = "reactivesw-bus";

  private TopicName topicName;

  public GoogleProducer(String projectId) throws IOException {
    topicName = TopicName.create(projectId, topicNameString);

    publisherClient = PublisherClient.create();

    publisher = Publisher.newBuilder(topicName).build();
  }

  public GoogleProducer(String projectId, String topicNameString) throws IOException {
    Assert.notNull(topicNameString);

    this.topicNameString = topicNameString;
    topicName = TopicName.create(projectId, topicNameString);
    publisher = Publisher.newBuilder(topicName).build();
  }

  @Override
  public Future<String> send(Message msg) {

    PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
        .setData(ByteString.copyFrom(jsonSerializer.serialize(msg)))
        .setMessageId(msg.id).build();

    return publisher.publish(pubsubMessage);
  }

  @Override
  public Future<String> send(Message msg, Callback callback) {
    return null;
  }

  @Override
  public void flush() {
  }

  @Override
  public void close() throws Exception {
    publisher.shutdown();
  }
}

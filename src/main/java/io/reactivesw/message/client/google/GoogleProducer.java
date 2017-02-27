package io.reactivesw.message.client.google;

import com.google.cloud.pubsub.spi.v1.Publisher;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.producer.Callback;
import io.reactivesw.message.client.producer.Producer;
import io.reactivesw.message.client.utils.serializer.JsonSerializer;
import javafx.util.converter.ByteStringConverter;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Google pub/sub producer client.
 */
public class GoogleProducer<T> implements Producer<T> {

  /**
   * google publisher.
   */
  Publisher publisher;
  JsonSerializer jsonSerializer = new JsonSerializer();

  public GoogleProducer() throws IOException {
    TopicName topicName = TopicName.newBuilder().setTopic("default").build();
    publisher = Publisher.newBuilder(topicName).build();
  }

  public GoogleProducer(String topicName) throws IOException {
    TopicName tn = TopicName.newBuilder().setTopic(topicName).build();
    publisher = Publisher.newBuilder(tn).build();
  }

  @Override
  public Future<String> send(Message<T> msg) {
    ByteStringConverter converter = new ByteStringConverter();
    return publisher.publish(null);
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

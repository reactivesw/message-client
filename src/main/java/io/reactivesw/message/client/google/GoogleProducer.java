package io.reactivesw.message.client.google;

import com.google.cloud.pubsub.spi.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.producer.Callback;
import io.reactivesw.message.client.producer.Producer;
import io.reactivesw.message.client.producer.ProducerCreateException;
import io.reactivesw.message.client.utils.serializer.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Google pub/sub producer client.
 */
public class GoogleProducer implements Producer {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(GoogleProducer.class);

  /**
   * google publisher.
   */
  private transient Publisher publisher;

  /**
   * json serializer.
   */
  private transient JsonSerializer jsonSerializer = new JsonSerializer();

  /**
   * constructor with project id, and topic name.
   *
   * @param projectId       String project name on google pub/sub
   * @param topicNameString topic name of the project
   * @throws IOException Throw IO exception when build publisher
   */
  public GoogleProducer(String projectId, String topicNameString) throws ProducerCreateException {
    Assert.notNull(projectId, "Google Pub/Sub project Id is null.");
    Assert.notNull(topicNameString, " Google Pub/Sub topic name is null.");
    LOG.debug("projectId: {}, topicName: {}.", projectId, topicNameString);

    TopicName topicName = TopicName.create(projectId, topicNameString);

    try {
      publisher = Publisher.newBuilder(topicName).build();
    } catch (IOException ex) {
      LOG.debug("create google producer failed. projectId: {}, topicName: {}.", projectId,
          topicNameString, ex);
      throw new ProducerCreateException("Create google producer failed.");
    }
  }

  /**
   * publish a message to the pub/sub.
   *
   * @param msg the detail message.
   * @return future.The message Id wrapped in the future.
   */
  @Override
  public Future<String> publish(Message msg) {

    PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
        .setData(ByteString.copyFromUtf8(jsonSerializer.serialize(msg)))
        .setMessageId(msg.getId()).build();

    return publisher.publish(pubsubMessage);
  }

  /**
   * add callback when publish message.
   *
   * @param msg      message
   * @param callback callback
   */
  @Override
  public void publish(Message msg, Callback callback) {
    //do nothing.
  }

  /**
   * flush.
   */
  @Override
  public void flush() {
    // flush.
  }

  /**
   * close.
   *
   * @throws Exception
   */
  @Override
  public void close() throws Exception {
    publisher.shutdown();
  }
}

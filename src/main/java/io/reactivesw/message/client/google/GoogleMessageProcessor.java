package io.reactivesw.message.client.google;

import com.google.cloud.pubsub.spi.v1.Subscriber;
import io.reactivesw.message.client.consumer.MessageProcessor;

/**
 * google message processor.
 */
public class GoogleMessageProcessor extends Subscriber.SubscriberListener {

  MessageProcessor messageProcessor;

  public GoogleMessageProcessor() {
    super();
  }

  @Override
  public void failed(Subscriber.State from, Throwable failure) {
    super.failed(from, failure);
  }

  @Override
  public void running() {
    messageProcessor.process();
  }

  @Override
  public void starting() {
    super.starting();
  }

  @Override
  public void stopping(Subscriber.State from) {
    super.stopping(from);
  }

  @Override
  public void terminated(Subscriber.State from) {
    super.terminated(from);
  }
}

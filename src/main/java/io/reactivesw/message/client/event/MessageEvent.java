package io.reactivesw.message.client.event;

import org.springframework.context.ApplicationEvent;


/**
 * Base class for events.
 */
@SuppressWarnings("serial")
public abstract class MessageEvent extends ApplicationEvent {

  public MessageEvent(Object source) {
    super(source);
  }

}

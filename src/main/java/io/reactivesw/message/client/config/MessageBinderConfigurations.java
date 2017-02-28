package io.reactivesw.message.client.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * message binder configurations.
 */
@Configuration
@Data
public class MessageBinderConfigurations {

  @Value("${io.reactivesw.message.broker.host:localhost}")
  private String broker;

  @Value("${io.reactivesw.message.broker.port: 9999}")
  private String port;

  @Value("${io.reactivesw.message.broker.user:reactivesw}")
  private String user;

  @Value("${io.reactivesw.message.broker.password: password}")
  private String password;

  @Value("${io.reactivesw.message.broker.type:google}")
  private String brokerType;

  @Value("${io.reactivesw.message.topic}")
  private String topic;

  @Value("${io.reactivesw.message.google.project.id:googleId}")
  private String googleProjectId;
}

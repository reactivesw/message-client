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

  /**
   * broker host.
   */
  @Value("${io.reactivesw.message.broker.host:localhost}")
  private String broker;

  /**
   * broker port.
   */
  @Value("${io.reactivesw.message.broker.port: 9999}")
  private String port;

  /**
   *
   */
  @Value("${io.reactivesw.message.broker.user:reactivesw}")
  private String user;

  /**
   * password.
   */
  @Value("${io.reactivesw.message.broker.password: password}")
  private String password;

  /**
   * broker type.
   */
  @Value("${io.reactivesw.message.broker.type:google}")
  private String brokerType;

}

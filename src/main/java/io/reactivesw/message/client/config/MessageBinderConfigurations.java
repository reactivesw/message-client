package io.reactivesw.message.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by umasuo on 17/2/27.
 */
@ConfigurationProperties(prefix = "io.reactivesw.message")
public class MessageBinderConfigurations {

  private String broker = "localhost";

  private String defaultBrokerPort = "0000";

  private String user = "reactivesw";

  private String password = "reactivesw-password";

  private String brokerType = "google";

}

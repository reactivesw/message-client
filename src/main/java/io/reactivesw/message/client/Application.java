package io.reactivesw.message.client;

import io.reactivesw.message.client.config.MessageBinderConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 17/2/9.
 */
@SpringBootApplication(scanBasePackages = "io.reactivesw")
@EnableAutoConfiguration
@RestController
public class Application {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);

  }

  @Autowired
  MessageBinderConfigurations messageBinderConfigurations;

  @GetMapping("/")
  public String test(){
    return messageBinderConfigurations.getGoogleProjectId();
  }
}

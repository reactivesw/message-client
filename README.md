# message-client
A message client for message bus.

# Introduction
This project provide a client for send/receive messages to different broker by the same way. 
We can change our message broker(such as kafka, redis, rabbitMQ, google pub/sub, AWS kinesis etc.), with out change our code, all we need to do is change our configurations.

# Currently Supported Broker
- Google pub/sub

# Message format
We use json for our message format. And we have `io.reactivesw.message.client.core.Message` for message body.

# Message Publisher
Publisher publish the message to the message bus.
## Google pub/sub
Google sub/sub service.
### Configs needed
- `io.reactivesw.message.broker.type` set to `google`
- `io.reactivesw.message.topic` is the topic you created in google pub/sub console
- `io.reactivesw.message.google.project.id` is the project you created in google cloud
### Create
```Java
    // only if you used gke or on you local(already set up the gcloud)
    DefaultProducerFactory defaultProducerFactory = new DefaultProducerFactory(mbConfig);
    Producer producer = defaultProducerFactory.createProducer();
```
### Publish message
```
    Message msg = ****;// your message

    producer.send(msg);
```

# Message consumer
Consumer receive & process the messages from the bus.
 
## Google pub/sub
Google sub/sub service.
### Configs needed
- `io.reactivesw.message.broker.type` set to `google`
- `io.reactivesw.message.google.project.id` is the project you created in google cloud
- `io.reactivesw.message.subscription` is the subscription you created in google pub/sub console

### Create 
```Java
    DefaultConsumerFactory defaultConsumerFactory = new DefaultConsumerFactory(mbConfig);
    Consumer consumer = defaultConsumerFactory.createConsumer();
```
### Rceive message
```Java
    List<Message> messages = consumer.pullMessages(5);
    //process the messages
```

# Dependency
Different broker need different libs to connected.
## Google pub/sub for gradle
```
    compile('com.google.api-client:google-api-client:1.22.0')
    compile('com.google.cloud:google-cloud-pubsub:0.9.3-alpha')
    compile('com.google.guava:guava:21.0')
```
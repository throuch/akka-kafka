# akka stream - kafka
Code sample for Scala developpers

Helper trait to be able to publish any object as a JSON message to a Kafka broker as simply as possible

```
 .... with ThornKafkaProducer ... {

...
 implicit override val formats = MyJsonConverter.formats // NOT REQUIRED if object to serialize uses only basic types (see json4s for details)
 implicit val  m: ActorMaterializer = ActorMaterializer()
...
 pushMessageToKafka(event, TOPIC_NAME)
...

}

```




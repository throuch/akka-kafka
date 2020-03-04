
import com.typesafe.config.Config
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.json4s.jackson.Serialization.write
import org.json4s.DefaultFormats

trait ThornKafkaProducer {
  val config = context.system.settings.config.getConfig("akka.kafka.producer")
   implicit val formats = DefaultFormats

  lazy val producerSettings =
    ProducerSettings(config, new StringSerializer, new StringSerializer)
  lazy val kafkaProducer = producerSettings.createKafkaProducer()


  def pushMessageToKafka(event: AnyRef, topicName: String)(implicit mat: Materializer) : Unit = {
    Source.single(write(event))
      .map( new ProducerRecord[String, String](topicName, _))
      .runWith(Producer.plainSink(producerSettings, kafkaProducer))
  }
}

import akka.actor.FSM.->
import kafka.serializer.StringDecoder
import org.apache.hadoop.conf.Configuration
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.{rdd, SparkContext, SparkConf}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.apache.spark.streaming.kafka._

object sparkStreeming {
  def main(args: Array[String])
  {

    val conf = new SparkConf().setAppName("Simple Application")
    conf.set( "spark.app.id", "1" )
    val ssc = new StreamingContext(conf, Seconds(10))

    //Kafka
    val topicsSet = Map("mychannel" -> 1)
    val lines: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, "quickstart:2181/kafka", "my-consumer-group", topicsSet )

    val data = lines.map{ case(k,v) => v.split(" ") }
    val data2 = data.map{ x => (x(0),x(1)) }
    val data3 = data2.map{ case(k,v) => (k, 1L) }
    val data4 = data3.reduceByKey( _+_ )

    data4.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()

  }
}

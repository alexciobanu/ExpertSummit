import akka.actor.FSM.->
import kafka.serializer.StringDecoder
import org.apache.hadoop.conf.Configuration
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka._

object sparkTest {
  def main(args: Array[String])
  {

    val conf = new SparkConf().setAppName("Simple Application")
    conf.set( "spark.app.id", "1" )
    val ssc = new StreamingContext(conf, Seconds(10))

    //Kafka
    val topicsSet = Map("mychannel" -> 1)
    val lines: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, "quickstart:2181/kafka", "my-consumer-group", topicsSet )

    val data = lines.map{ case(k,v) => v }

    data.print()

    //Rgular Streeming
    /*val lines = ssc.socketTextStream("quickstart", 12345)

    val logFile = "output.txt" // Should be some file on your system
    val sc = new SparkContext(conf)

    val numAs = lines.filter(line => line.contains("a")).count()
    val numBs = lines.filter(line => line.contains("b")).count()

    lines.print()
    numAs print()
    numBs.print()
    */

    ssc.start()             // Start the computation
    ssc.awaitTermination()

    //Normal Spark
    //val lines = sc.textFile(logFile, 2)
    //println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
    //sc.stop()

  }
}

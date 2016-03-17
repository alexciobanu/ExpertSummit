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

    //val data5 = data3.reduceByKeyAndWindow( (value:String, value1:String) => value + value1 , Minutes(10), Seconds(10), 1 )

    data4.print()

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

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.{rdd, SparkContext, SparkConf}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.apache.spark.streaming.kafka._

object simpleStreeming {
  def main(args: Array[String])
  {

    val conf = new SparkConf().setAppName("Simple Application")
    conf.set( "spark.app.id", "1" )
    val ssc = new StreamingContext(conf, Seconds(10))


    val lines = ssc.socketTextStream("quickstart", 12345)

    val numAs = lines.filter(line => line.contains("a")).count()
    val numBs = lines.filter(line => line.contains("b")).count()

    lines.print()
    numAs print()
    numBs.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()

  }
}

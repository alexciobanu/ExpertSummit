
import org.apache.spark.{rdd, SparkContext, SparkConf}


object simpleSpark {
  def main(args: Array[String])
  {

  val conf = new SparkConf().setAppName("Simple Application")
  val logFile = "FlumeData.1457910781124" // Should be some file on your system
  val sc = new SparkContext(conf)
  val lines = sc.textFile(logFile, 2)

  val numAs = lines.filter(line => line.contains("a")).count()
  val numBs = lines.filter(line => line.contains("b")).count()
  println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  sc.stop()

  }
}

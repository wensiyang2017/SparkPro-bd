package com.itcast.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

class WindowOperationStreaming {

  def main(args: Array[String]): Unit = {


    val conf = new SparkConf
    conf.setAppName("windowreduce").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(2))
    ssc.checkpoint(".")

    val sockDs = ssc.socketTextStream("localhost", 9999)
    val wordDs = sockDs.flatMap(x => x.split(" ")).map(x => (x, 1))

    //1:对V进行操作的操作
    val wordCountDs = wordDs.reduceByKeyAndWindow(_ + _, _ - _, Seconds(6), Seconds(4))
    wordCountDs.print()
    ssc.start()
    ssc.awaitTermination()

  }

}

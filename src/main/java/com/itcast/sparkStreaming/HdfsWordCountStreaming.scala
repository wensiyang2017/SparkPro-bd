package com.itcast.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

class HdfsWordCountStreaming {

  def main(arg: Array[String]): Unit = {

    if (arg.length < 1) {
      System.err.print("Usage:HafsWordCountStrasming < hdfs - path>")
      System.exit(1)
    }

    val conf = new SparkConf()
    conf.setAppName("hdfsWordcount")
      .setMaster("local[2]")

    val ssc = new StreamingContext(conf, Seconds(2))
    val fileDstream = ssc.textFileStream(arg(0))

    //开始切分单词(此处对dstream操作是针对dstream中的每一批rdd)
    val words = fileDstream.flatMap(x => x.split(" "))
      .map(x => (x, 1))

    val wordcounts = words.reduceByKey(_ + _)

    wordcounts.print()
    ssc.start()
    ssc.awaitTermination()


  }

}

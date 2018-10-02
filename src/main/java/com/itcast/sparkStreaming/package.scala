package com.itcast

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  *
  * 数据源是socket
  * */
package object SocketWordcount {


  def main(args: Array[String]): Unit = {


    val conf = new SparkConf()
    conf.setAppName("socketWordcount")
      .setMaster("local[2]")


    //创建一个sprarkStreaming ,单位时间内的rdd的list
    val ssc = new StreamingContext(conf, Seconds(2))

    //数据源有很多
    val stDStream = ssc.socketTextStream("localhost", 8888, StorageLevel.MEMORY_AND_DISK)

    //开始切分单词(此处对dstream操作是针对dstream中的每一批rdd)
    val words = stDStream.flatMap(x => x.split(" "))
      .map(x => (x, 1))

    val wordcounts = words.reduceByKey(_ + _)

    wordcounts.print()

    ssc.start()
    ssc.awaitTermination()
  }

}

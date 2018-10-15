package com.itcast.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

class StatefulWordCountStreaming {

}


object StatefulWordCountStreaming {

  def main(args: Array[String]): Unit = {


    val conf = new SparkConf()
    conf.setMaster("local[2]").setAppName("statewc")
    val ssc = new StreamingContext(conf, Seconds(2))

    val socketDs = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_AND_DISK)
    val wordDs = socketDs.flatMap(x => x.split(" ")).map(x => (x, 1))

    //更新状态
    //wordDs.updateStateByKey(updateFunc)

  }

 // val updateFunc = (iter:Iterator[(Str)])


}
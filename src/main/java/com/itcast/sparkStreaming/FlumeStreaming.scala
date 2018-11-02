package com.itcast.sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.flume.FlumeUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.scalatest.time.Second


/**
  * Streaming集成Flume
  **/
class FlumeStreaming {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setMaster("local[2]").setAppName("flumeStreaming")
    val ssc = new StreamingContext(conf, Seconds(2))
    //FlumeUtils.createStream(ssc,"")

  }

}

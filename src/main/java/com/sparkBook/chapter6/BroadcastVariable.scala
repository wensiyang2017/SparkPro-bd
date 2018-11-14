package com.sparkBook.chapter6

import org.apache.spark.{SparkConf, SparkContext}


/**
  *
  * 广播变量
  * driver端修改,excutor使用
  *
  **/
class BroadcastVariable {

}

object BroadcastVariable {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("accumulator").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val list = List("ERROR")
    val broadcast = sc.broadcast(list)
    val lines = sc.textFile("/Users/wensiyang/Documents/随手记/date.txt")
    lines.flatMap(_.split(" "))
      .filter(_.contains(broadcast.value(0)))
      .foreach(println)
    sc.stop()

  }
}

package com.sparkBook


import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

/**
  * wordCount
  */
object WordCountDemo {


  /**
    *
    * 操作Spark(Driver Program)集群是通过SparkContext对象进行的
    * SparkContext作为操作和调度的总入口,
    * 初始化的过程中会创建DAGScheduler作业调度和TaskScheduler任务调度
    *
    **/
  def main(args: Array[String]): Unit = {
    val masterUrl = "local[2]"

    //构造SparkConf对象进行参数配置
    val conf = new SparkConf()
    //masterUrl 对应不同运行模式:local[*](本地)spark://master:port
    conf.setMaster(masterUrl).setAppName("wordCount")

    //构造一个sparkContext
    val sc = new SparkContext(sc)
    val lines = sc.textFile("D:/wordCount.txt")
    lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)
          .saveAsTextFile("D:/wordCountText.txt")
    sc.stop()
  }

}

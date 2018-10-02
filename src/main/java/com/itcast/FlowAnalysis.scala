package com.itcast

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @: 流量数据汇总排序输出
  * @: wsy
  * @: Created in 10:58 2018/9/29
  * @: $v: 1.0.0
  */
class FlowAnalysis {


  def main(args: Array[String]): Unit = {
    val masterUrl = "local[2]"
    val input = "c:/xxx"
    val output = "d:/xxx"
    val conf = new SparkConf()

    conf.setMaster(masterUrl)
      .setAppName("flow")
    val sc = new SparkContext(conf)

    val lineRdd = sc.textFile(input).map(x => (x.split(" \t")))
    lineRdd.cache() //Array(Array(String)) 每一行都是一个数组,

    //??? 为什么上面那不不用flatMap,因为要取出每个数组的
    //提取数据流量rdd[(phone:string,(upFlow:long,downFlow:long))]
    lineRdd.map(x => (x(1), (x(x.length - 3).toLong, x(x.length - 1).toLong)))
      .reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
      .sortBy(x=>x._2._2,false)


  }
}

package com.itcast.wordCount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @: 流量数据汇总排序输出--利用bean来包装
  * @: wsy
  * @: Created in 10:58 2018/9/29
  * @: $v: 1.0.0
  */
class FlowAnalysis2 {


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

    val beanRdd = lineRdd.map(x => (x(1), FlowBean(x(1), x(x.length - 3).toLong, x(x.length - 2).toLong)))
    //reduceByKey,就是把相同的key的每一个取出来x,(x,y)
    beanRdd.reduceByKey((bean1, bean2) => FlowBean(bean1.phone, bean1.upFlow + bean2.upFlow, bean1.dFlow + bean2.dFlow))
      .map(x => (x._2.sumFlow, x)).sortByKey(false)
    sc.stop()

  }

  case class FlowBean(val phone: String, val upFlow: Long, val dFlow: Long) {
    val sumFlow = upFlow + dFlow
  }

}

package com.sparkBook.chapter3

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 常用TF算子
  */
object TransformationDemo {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("local").setMaster("Local[*]")
    val sc = new SparkContext(conf)
    val kv1 = sc.parallelize(List(("A",1),("B",2),("C",3),("A",4)))
    val kv2 = sc.parallelize(List(("A",4),("B",6),("C",3),("A",6)))
  }

}

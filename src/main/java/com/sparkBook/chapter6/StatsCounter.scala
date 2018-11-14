package com.sparkBook.chapter6

import org.apache.spark.{SparkConf, SparkContext}

class StatsCounter {

}

object StatsCounter {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("accumulator").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val paraRdd = sc.parallelize(1 to 10)
    val s = paraRdd.stats()
    println(s.count)
    println(s.mean)
    println(s.max)
    println(s.sum)
    println(s.min)
  }

}

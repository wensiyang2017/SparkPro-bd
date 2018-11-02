package com.itcast.deepSpark

import org.apache.spark.{SparkConf, SparkContext}

class TestBasic {

}


object TestBasic {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("test").setMaster("spark://172.16.49.10")
    val sc = new SparkContext(conf)

    val rdd0 = sc.parallelize(List(1, 2, 3, 4, 5, 6, 7))
    val count = rdd0.count()

  }


}

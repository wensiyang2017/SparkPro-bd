package com.itcast.deepSpark

import org.apache.spark.{SparkConf, SparkContext}

class TestBasic {

}


object TestBasic {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    //conf.setAppName("test").setMaster("spark://172.16.49.10")
    conf.setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val inputRdd = sc.textFile("/Users/wensiyang/Documents/随手记/date.txt")
    //val rddWcount =rdd0.flatMap(_.split(" ")).map(x=>(x,1)).reduceByKey(_+_)
    val errorRdd = inputRdd.filter(line => line.contains("error")||line.contains("ERROR"))
    val errorCount = errorRdd.count()
    println("Input error " + errorCount + " concerning lines")
    println("Here are "+ errorCount +" examples:")
    errorRdd.take(errorCount.toInt).foreach(println)
  }


}

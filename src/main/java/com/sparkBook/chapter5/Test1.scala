package com.sparkBook.chapter5

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

class Test1 {

}


object Test1 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("hive")
    val sc = new SparkContext(conf)
    val hiveCtx = new org.apache.spark.sql.hive.HiveContext(sc)
    val rows = hiveCtx.sql("SELECT name, age FROM users")
    val firstRow = rows.first()
    println(firstRow.getString(0)) // 字段0是name字段
  }

}
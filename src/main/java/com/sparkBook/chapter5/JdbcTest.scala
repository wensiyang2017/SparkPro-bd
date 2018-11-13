package com.sparkBook.chapter5
import java.sql.{Connection, DriverManager, ResultSet}

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.JdbcRDD

object JdbcTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("mysql")
    val sc = new SparkContext(conf)
    val data = new JdbcRDD(sc,
      createConnection, "SELECT * FROM panda WHERE ? <= id AND id <= ?",
      lowerBound = 1, upperBound = 3, numPartitions = 2, mapRow = extractValues)
    println(data.collect().toList)
  }

  def createConnection(): Connection = {

    //创建驱动
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    //获取连接
    DriverManager.getConnection("jdbc:mysql://localhost:3306/test?username=root&password=123456")


  }

  def extractValues(r: ResultSet) = {
    (r.getInt(1), r.getString(2))
  }

}



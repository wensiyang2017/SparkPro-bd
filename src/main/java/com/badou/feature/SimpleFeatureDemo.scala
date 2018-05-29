package com.badou.feature

import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object SimpleFeatureDemo {
  def main(args: Array[String]): Unit = {
    //https://blog.csdn.net/u013063153/article/details/54615378
    /*
    *  在2.0版本之前，使用Spark必须先创建SparkConf和SparkContext，代码如下：
    *   //设置spark配置并创建上下文
    *   val sparkConf = new SparkConf().setAppName("example").setMaster("Local")
    *   val sc = new SparkContext(sparkConf)
    *   //让spark访问sql
    *    val spark = new org.apache.spark.sql.SQLContext(sc)
    *
    *   Spark2.0中只要创建一个SparkSession就够了，
    *   SparkConf、SparkContext和SQLContext都已经被封装在SparkSession当中。
    *
    *   val spark = SparkSession
    *               .builder()
    *               .appName("SparkSessionZipsExample")
    *               .config("spark.sql.warehouse.dir", warehouseLocation)
    *               .enableHiveSupport()
    *               .getOrCreate()
    *
    *    // spark 设置运行参数
    *     spark.conf.set("spark.sql.shuffle.partitions", 6)
    *     spark.conf.set("spark.executor.memory", "2g")
    *
    * */

    val spark = SparkSession.builder().appName("Produc_LR").enableHiveSupport().getOrCreate();

    val priors = spark.sql("select * from baodu.priors");
    val orders = spark.sql("")

  }

  def Feat(priors: DataFrame, orders: DataFrame): DataFrame = {


  } asse
}

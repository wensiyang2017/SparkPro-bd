package com.sparkBook.chapter4

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 常见action
  */
object ActionDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("myApp").setMaster("local")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/data/test.txt")

    //Hadoop数据集count就是行数
    lines.count()

    val arr = sc.parallelize(Array(1, 2, 3, 4, 5, 6, 687, 6))

    arr.reduce((x,y)=>x+y)  //所有元素相加

    //返回rdd前n个
    arr.take(2)

    val kv1 = sc.parallelize(List(("A",1),("B",2),("C",3),("A",4)))
    //scala.collection.Map[String,Long] = Map(B -> 1, A -> 2, C -> 1)
    kv1.countByKey()

    val rdds = sc.parallelize(1 to 1000)
    rdds.foreach(_+1)//没有修改元Rdds
  }

}

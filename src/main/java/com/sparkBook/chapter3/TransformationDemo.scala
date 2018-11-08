package com.sparkBook.chapter3

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 常用TF算子(变化操作)
  */
object TransformationDemo {


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("local").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //    val kv1 = sc.parallelize(List(("A",1),("B",2),("C",3),("A",4)))
    //    val kv2 = sc.parallelize(List(("A",4),("B",6),("C",3),("A",6)))
    /*
    *
    * 对一个数据为{1, 2, 3, 3}的RDD进行基本的RDD转化操作
    *
    * */
    //val rdd = sc.parallelize(Array(1, 2, 3, 3))
    //rdd.map(x => x + 1).take(4)
    //rdd.flatMap(x => x.to(3))
    //rdd.filter(_ != 1)
    //rdd.distinct()
    //rdd.sample(false,0.5)
    //  .foreach(println)
    val rdd1 = sc.parallelize(Array(1, 2, 3))
    val rdd2 = sc.parallelize(Array(3, 4, 5))
    //并集
    rdd1.union(rdd2)
    //交集 3
    rdd1.intersection(rdd2)
    //移除一个rdd的内容(移除训练集)
    rdd1.subtract(rdd2)
      .foreach(print)

  }

}

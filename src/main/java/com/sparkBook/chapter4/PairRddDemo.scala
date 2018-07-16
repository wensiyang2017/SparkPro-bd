package com.sparkBook.chapter4

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 键值对操作 (reduceByKey,groupByKey)
  */
object PairRddDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("local").setMaster("Local")
    val sc = new SparkContext(conf)
    val kv1 = sc.parallelize(List(("A",1),("B",2),("C",3),("A",4)))
    val kv2 = sc.parallelize(List(("A",4),("B",6),("C",3),("A",6)))
    kv1.sortByKey().collect() //Array[(String, Int)] = Array((A,1), (A,4), (B,2), (C,3))
    kv1.sortByKey(false).collect() //Array[(String, Int)] = Array((C,3), (B,2), (A,1), (A,4))

    // Array[(String, Iterable[Int])] = Array((B,CompactBuffer(2)), (A,CompactBuffer(4, 1)), (C,CompactBuffer(3)))
    kv1.groupByKey()//对相同健的值进行分组

    //Array[(String, Int)] = Array((B,2), (A,5), (C,3))
    //解释:reduceByKey将相同的K的V当成一个Array后做rduce处理[见:scala reduce函数] (_+_)当前和下个的和,也可以用((x,y)=>x+y)
    //kv1.reduceByKey(fuc) 合并相同的健的值
    kv1.reduceByKey(_+_).collect
    kv1.reduceByKey((x,y)=>x+y).collect

    // Array[(String, (Int, Int))] = Array((A,(1,1)), (B,(2,1)), (C,(3,1)), (A,(4,1)))
  }
}


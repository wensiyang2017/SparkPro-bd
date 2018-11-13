package com.sparkBook.chapter4

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 键值对操作 (reduceByKey,groupByKey)
  */
object PairRddDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("local").setMaster("local")
    val sc = new SparkContext(conf)
    val kv1 = sc.parallelize(List(("A", 1), ("B", 2), ("C", 3), ("A", 4), ("D", 1)))
    val kv2 = sc.parallelize(List(("A", 2), ("B", 6), ("C", 3), ("A", 6)))
    kv1.sortByKey().collect() //Array[(String, Int)] = Array((A,1), (A,4), (B,2), (C,3))
    kv1.sortByKey(false).collect() //Array[(String, Int)] = Array((C,3), (B,2), (A,1), (A,4))

    // Array[(String, Iterable[Int])] = Array((B,CompactBuffer(2)), (A,CompactBuffer(4, 1)), (C,CompactBuffer(3)))
    //println("groupByKey" + kv1.groupByKey())
    //对相同健的值进行分组
    val t = kv1.groupByKey()
    //t.foreach(println)

    //Array[(String, Int)] = Array((B,2), (A,5), (C,3))
    //解释:reduceByKey将相同的K的V当成一个Array后做rduce处理[见:scala reduce函数] (_+_)当前和下个的和,也可以用((x,y)=>x+y)
    //kv1.reduceByKey(fuc) 合并相同的健的值
    kv1.reduceByKey(_ + _).collect
    kv1.reduceByKey((x, y) => x + y).collect


    // Array[(String, (Int, Int))] = Array((A,(1,1)), (B,(2,1)), (C,(3,1)), (A,(4,1)))
    //对 pair RDD 中的每个值应用 一个函数而不改变键
    kv1.mapValues(x => (x, 1)).collect()
    kv1.mapValues(x => x + 1).collect()

    //(A,1)(A,2)(A,3)(A,4)(A,5)(B,2)(B,3)(B,4)(B,5)(C,3)(C,4)(C,5)(A,4)(A,5)
    val flatKV = kv1.flatMapValues(x => (x to 5))
    //flatKV.foreach(print)

    val rddKeys = kv1.keys
    val rddValues = kv1.values
    //println(rddKeys +""+ rddValues)


    /** ***************两个pairRdd ********************************/
    /** ***************两个pairRdd ********************************/

    //把相同的key减去
    val subRdd = kv1.subtractByKey(kv2)
    //subRdd.foreach(println)

    //join (k,v),(k,w)=>(k,(v,w))相同的可以value笛卡尔积
    //(B,(2,6))
    //(A,(1,2))
    //(A,(1,6))
    //(A,(4,2))
    //(A,(4,6))
    //(C,(3,3))
    val joinRdd = kv1.join(kv2)
    //joinRdd.foreach(println)

    //左外连接 左边的一定存在
    val joinLeftRdd = kv1.leftOuterJoin(kv2)
    //joinLeftRdd.foreach(println)

    //右外连接 右边的一定存在
    val joinRightRdd = kv1.rightOuterJoin(kv2)
    //joinRightRdd.foreach(println)

    //cogroup (k,v),(k,w)=>(k,(c(v),c(w)))
    val cogroupRdd = kv1.cogroup(kv2)
    //cogroupRdd.foreach(println)


    kv1.filter(_._2 > 2)
    //仅对pair RDD的值部分进行操作
    kv1.mapValues(x => x + 1)
    /*
    * 计算每个键对应的平均值
    */
    /**
      * 思路((A,1),(A,2)) =>求出总和/个数
      * 一:
      *   a. reduceByKey(_+_) ==>(A,3)
      *   b. 个数就是相当于统计词频率reduceByKey(_+_)
      *   c. 合并上面两步骤
      */
    //    kv1.reduceByKey(_ + _) //a
    //    kv1.mapValues(_ => 1L) // b
    //      .reduceByKey(_ + _)

    val avgRdd = kv1.mapValues(x => (x, 1))
      .reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
      .mapValues(x => x._1.toDouble / x._2.toDouble)
    //.map(x => (x._1, x._2._1.toDouble / x._2._2.toDouble)) //与上面步骤效果一样
    //avgRdd.foreach(println) //c

    kv1.groupByKey()
      .map(x => (x._1, x._2.sum, x._2.max, x._2.min))
    //.foreach(println(_))

    val result = kv1.combineByKey(
      (v) => (v, 1),
      (acc: (Int, Int), v) => (acc._1 + v, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
    ).map { case (key, value) => (key, value._1 / value._2.toFloat) }
    result.collectAsMap().map(println(_))

    kv1.countByKey().foreach(println(_))


    sc.stop()
  }
}


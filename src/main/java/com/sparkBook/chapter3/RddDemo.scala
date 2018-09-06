package com.sparkBook.chapter3

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession


object RddDemo {
  /**
    * 在2.0版本之前，使用Spark必须先创建SparkConf和SparkContext，代码如下：
    * //设置spark配置并创建上下文
    * val sparkConf = new SparkConf().setAppName("example").setMaster("Local")
    * val sc = new SparkContext(sparkConf)
    * //让spark访问sql
    * val spark = new org.apache.spark.sql.SQLContext(sc)
    *
    * Spark2.0中只要创建一个SparkSession就够了，
    * SparkConf、SparkContext和SQLContext都已经被封装在SparkSession当中。
    *
    * val spark = SparkSession
    * .builder()
    * .appName("SparkSessionZipsExample")
    * .config("spark.sql.warehouse.dir", warehouseLocation)
    * .enableHiveSupport()
    * .getOrCreate()
    *
    * // spark 设置运行参数
    *     spark.conf.set("spark.sql.shuffle.partitions", 6)
    *     spark.conf.set("spark.executor.memory", "2g")
    *
    **/

  val spark = SparkSession
    .builder()
    .appName("myApp")
    .getOrCreate()
  val sparkConf = new SparkConf().setAppName("example").setMaster("Local")
  val sc = new SparkContext(sparkConf)


  /**
    * Application:基于Spark的用户程序，包含了一个driver program和集群中多个executor
    *
    * Driver Program：运行Application的main()函数并创建SparkContext。通常SparkContext代表driver program
    *
    * Executor：为某Application运行在worker node上的一个进程(内存+线程)。
    *           该进程负责运行Task，并负责将数据存在内存或者磁盘上。每个Application都有自己独立的executors
    *
    * Cluster Manager: 在集群上获得资源的外部服务（例如 Spark Standalon，Mesos、Yarn）
    *
    * Worker Node: 集群中任何可运行Application代码的节点
    *
    * Job：可以被拆分成Task并行计算的工作单元，一般由Spark Action触发的一次执行作业。
    *
    * Stage：每个Job会被拆分成很多组Task，每组任务被称为stage，也可称TaskSet。该术语可以经常在日志中看到。
    *
    * Task：被送到executor上执行的工作单元。
    *
    **/

  /**
    * Rdd : 弹性分布式数据集
    *
    * A list of partitions 分区的集合
    * A function for computing each split
    * A list of dependencies on other RDDs 依赖其他集合
    * Optionally, a Partitioner for key-value RDDs (e.g. to say that the RDD is hash-partitioned)
    * Optionally, a list of preferred locations to compute each split on (e.g. block locations for an HDFS file)
    *
    **/
  /*
   *  1.并行化数据集
   *  parallelize并行化 故该rdd是并行化数据集
  * */
  val rdds1 = sc.parallelize(List("a", "b", "c"))
  val rdds2 = sc.parallelize(Array(1 to 10), 6)
  /*
   *  2.Hadoop数据集
   *
  * */
  val rddsH = sc.textFile("/data/The_man_of_property.txt")

  //wordCount
  rddsH.flatMap(_.split(" ")).map(x=>(x,1)).reduceByKey(_+_)
}

package com.sparkBook.chapter3

import org.apache.spark.{SparkConf, SparkContext}

class ErrorLineCount {

}

/**
  *
  *  包含"error"的行数统计
  **/
object ErrorLineCount {


  def main(args: Array[String]): Unit = {


    /*
    *  我们在驱动器程序中使用 take() 获取了 RDD 中的少量元素。然后在本地 遍历这些元素，并在驱动器端打印出来。
    *  RDD 还有一个 collect() 函数，可以用来获取整 个 RDD 中的数据。如果你的程序把 RDD 筛选到一个很小的规模，并且你想在本地处理 这些数据时，就可以使用它。
    *  记住，只有当你的整个数据集能在单台机器的内存中放得下 时，才能使用 collect()，因此，collect() 不能用在大规模数据集上。
    *
    *  在大多数情况下，RDD 不能通过 collect() 收集到驱动器进程中，因为它们一般都很大。
    *  此时，我们通常要把数据写到诸如 HDFS 或 Amazon S3 这样的分布式的存储系统中。你可 以使用 saveAsTextFile()、saveAsSequenceFile()，
    *  或者任意的其他行动操作来把 RDD 的 数据内容以各种自带的格式保存起来
    *
    * */


    val conf = new SparkConf()
    conf.setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val inputRdd = sc.textFile("/Users/wensiyang/Documents/随手记/date.txt")
    val errorRdd = inputRdd.filter(line => line.contains("error") || line.contains("ERROR"))
    val errorCount = errorRdd.count()
    println("Input error " + errorCount + " concerning lines")
    println("Here are " + errorCount + " examples:")
    errorRdd.take(errorCount.toInt).foreach(println)
  }
}
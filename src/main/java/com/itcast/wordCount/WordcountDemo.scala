package com.itcast.wordCount

import org.apache.spark.{SparkConf, SparkContext}


/**
  * 9/27 练习
  *
  **/
object WordcountDemo {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
    conf.setMaster("spark://192.168.173.10:7077")
    conf.setAppName("wordCount")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("/data/The_man_of_property.txt")
    lines.flatMap { x => x.split(" ") }
      .map { x => (x, 1) }
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
      .saveAsTextFile("/data/out.txt")
    sc.stop()
  }


  //  val intpath = "/BigData/tool/hapMirroring/share_folder/data/order_data/The_man_of_property.txt";
  //  val outpath = "/Users/wensiyang/Documents/out.txt";
  /* def main(args: Array[String]): Unit = {
     //创建一个conf对象
     val conf = new SparkConf()
     val intpath = "D:/wordCount.txt"
     val outpath = "D:/out.txt"
     //masterUrl对应几种不同的类型,相应的不同的运行模式

     /*
     * local[n] 人为定义线程数
     * local[*]  更具cpu自动选优
     * spark://master:port/
     * */
     conf.setMaster("local[1]")
       .setAppName("wordCount")

     //构建一个saprkContext
     val sc = new SparkContext(conf)
     val lines = sc.textFile(intpath)
     lines.flatMap { x => x.split(" ") }
       .map { x => (x, 1) }
       .reduceByKey(_ + _)
       .saveAsTextFile(outpath)
     sc.stop()
   }*/


}

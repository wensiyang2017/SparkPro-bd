package com.sparkBook.chapter6

import org.apache.spark.{SparkConf, SparkContext}

class Accumulator {

}

/**
  *
  * 累加器
  *
  **/
object Accumulator {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("accumulator").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val file = sc.textFile("/Users/wensiyang/Documents/随手记/date.txt")
    val blankLines = sc.accumulator(0) // 创建Accumulator[Int]并初始化为0  }

    val callSigns = file.flatMap(line => {
      if (line == "") {
        blankLines += 1 // 累加器加1
      }
      line.split(" ")
    })
    callSigns.saveAsTextFile("output.txt")
    println("Blank lines: " + blankLines.value)

  }
}

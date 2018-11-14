package com.sparkBook.chapter6

import org.apache.spark.{SparkConf, SparkContext}

class Accumulator {

}

/**
  *
  * 累加器
  * excutor使用端修改,driver读取
  **/
object Accumulator {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("accumulator").setMaster("local[*]")
    val sc = new SparkContext(conf)



    //findBlanklines(sc)
    countOuShu(sc)
    sc.stop()
  }

  def countOuShu(sc: SparkContext) = {
    val accum = sc.accumulator(0, "Error Accumulator")
    val data = sc.parallelize(1 to 10)
    //用accumulator统计偶数出现的次数，同时偶数返回0，奇数返回1
    val newData = data.map { x => {
      if (x % 2 == 0) {
        accum += 1
        0
      } else 1
    }
    }
    println(newData.count) //一个action操作
    //此时accum的值为5，是我们要的结果
    println(accum.value)

    //继续操作，查看刚才变动的数据,foreach也是action操作
    newData.foreach(print)
    //上个步骤没有进行累计器操作，可是累加器此时的结果已经是10了
    //这并不是我们想要的结果
    println(accum.value)

    /*
    *
    * 使用累加器的过程中只能使用一次action的操作才能保证结果的准确性。
    * 事实上，还是有解决方案的，只要将任务之间的依赖关系切断就可以了。
    * 什么方法有这种功能呢？你们肯定都想到了，cache，persist。
    * 调用这个方法的时候会将之前的依赖切除，后续的累加器就不会再被之前的transfrom操作影响到了。
    *
    * */

  }

  def findBlanklines(sc: SparkContext) = {
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

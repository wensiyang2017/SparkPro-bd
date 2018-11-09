package com.sparkBook.chapter3

import org.apache.spark.{SparkConf, SparkContext}

class ActionDemo {

}

/**
  *
  * 行为操作
  *
  **/
object ActionDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("action").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List(1, 2, 3, 4, 5), 2)
    val rdd2 = sc.parallelize(Array(("a", 1), ("b", 2), ("a", 2), ("c", 5), ("a", 3)), 2)

    /*
    * reduce它接收一个函数作为参数，这个函数要操作两个RDD 的元素类型的数据并返回一个同样类型的新元素
    * */
    println(rdd1.reduce(_ + _))
    println(rdd1.reduce(rddReduce))

    /*
    *
    * fold其实就是先对rdd分区的每一个分区进行使用op函数，
    * 在调用op函数过程中将zeroValue参与计算，最后在对每一个分区的结果调用op函数，
    * 同理此处zeroValue再次参与计算！
    *
    * 1+2+3+10=16 4+5+10=19 聚合在计算16+19+10 = 45
    *
    * */
    println(rdd1.fold(10)(add))


    /*
      * 1.开始时将(“d”,0)作为op函数的第一个参数传入，
      * 将Array中和第一个元素("a",1)作为op函数的第二个参数传入，并比较value的值， 返回value值较大的元素
      *
      * 2.将上一步返回的元素又作为op函数的第一个参数传入，Array的下一个元素作为op函数的第二个参数传入，比较大小
      * 3.重复第2步骤
      */
    val foldRDD = rdd2.fold(("d", 0))((val1, val2) => {
      if (val1._2 >= val2._2) val1 else val2
    })
    println("folder" + foldRDD)


    /*
      *(seqOp: (U, T) => U, combOp: (U, U) => U): U = withScope
      * 每个分区先计算seqOp ,在聚合计算combOP
      *FF
      */
    val rdd3 = sc.parallelize(List(2, 2, 2, 2), 1)
    //1+2+2 = 5 3+4+2 =9
    val aggregateRDD = rdd3.aggregate(1)(_ + _, _ * _)
    println("aggregateRDD : " + aggregateRDD)


    sc.stop
  }

  val add = (x: Int, y: Int) => {
    x + y
  }
  val rddReduce = (x: Int, y: Int) => x * y
}
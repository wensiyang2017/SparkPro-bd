package com.txkt.qf.day01

import scala.Array._

class Test6 {

}


object Test6 {


  def main(args: Array[String]): Unit = {
    test1
    test2
  }


  /*
*
* 数组的方法
*
* */
  def test2: Unit = {
    val arr = apply(1, 23, 45, 6, 7, "x")
    val arr1 = new Array[Object](5)
    val arr2 = Array(1,23,4,"x")

    for(i <- arr) println(i)

    for (i <- Array.apply(1, 10, 3, 4, 5, 66)) {
      println(i)
    }
  }

  /*
*
* 数组的定义
*
* */
  def test1: Unit = {

    //一:定义好类型和长度
    val arr1 = new Array[String](3)
    arr1(0) = "0"
    arr1(1) = "1"
    arr1(2) = "2"
    //arr1(5) = "0"  编译器不报错,运行期角标越界
    for (i <- arr1) {
      println(i)
    }

    //二:直接赋值
    val arr2 = Array(1111, 23, 4, 5, 6)
    for (j <- arr2) {
      println(j)
    }

    //三:创建区间数组
    val arr3 = range(10, 20, 2)
    val arr4 = range(10, 15)
    for (x <- arr3) {
      print(" " + x)
    }
    println()
    for (x <- arr4) {
      print(" " + x)
    }
    println()

    val arr5 = concat(arr3, arr4)
    for (i <- arr5) {
      println("arr5 : " + i)
    }
  }
}
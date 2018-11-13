package com.txkt.qf.day01

import scala.collection.immutable

class Test1 {

}

//静态类
object Test1 {

  def main(args: Array[String]): Unit = {
    val str = "aaa"
   // println(str)


    /*
    *
    * scala的7种类型
    * */
    //Byte Char Short Int Long Folat Double

    val x = 3
    val x1: String = "3"

    /*
    * 条件表达式
    * */
    val y = if (x > 1)  1 else -1
   // println(y)
    val z = if (x < 1) 1 else "erro" //混合类型表达式
    //println(z)

    val a = if(x<1) 1
    //print(a)  //AnyVal相当于JAVA的object


    val start: Int = 1
    val end: Int = 10
    val step: Int = 2
    val arr = test(start,end,step)
    for(i <- arr){
      println("arr : "+i)
    }

    for(i <- 0 to arr.length-1){
      println("arr("+ i +") : "+arr(i))
    }

  }

  def test(start: Int, end: Int, step: Int): Array[Int] = {
    if (step == 0) throw new IllegalArgumentException("zero step")

    //创建一个不定长的数组
    val b = Array.newBuilder[Int]
    var i = start

    b.sizeHint(immutable.Range.count(start, end, step, isInclusive = false))

    while((step<0 && end<i)||(step>0 && end>i)){
   // while (if (step < 0) end < i else i < end) {
      b += i
      i += step
    }
    b.result()
  }
}
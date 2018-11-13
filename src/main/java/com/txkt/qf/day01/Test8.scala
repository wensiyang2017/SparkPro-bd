package com.txkt.qf.day01

import scala.io.Source

class Test8 {

}

/**
  *
  * 映射和元组  映射是键/值对偶的集合
  **/
object Test8 {
  def main(args: Array[String]): Unit = {
    test5()
  }

  /** ************************* 练习 **********************************/
  /** ************************* 练习 **********************************/
  def test5(): Unit = {
    val map1 = Map("火麒麟" -> 888, "黄金Ak47" -> 999, "巴雷特" -> 128)
    val map2 = for ((k, v) <- map1) yield (k, 0.9 * v)
    for ((k, v) <- map2) {

      // println(k,v)

    }


    val lines = Source.fromFile("/Users/wensiyang/Documents/随手记/date.txt", "UTF-8").getLines()
    val hh = lines.flatMap(_.split(" "))
    val map = new scala.collection.mutable.HashMap[String, Int]
    var map3 = Map[String,Int]()
    for (h <- hh) {
      var countSize = map.getOrElse(h, 0)
      countSize += 1
      map += (h -> countSize)
//      if(!map.keySet.contains(h.toLowerCase)) map(h.toLowerCase) = 0
//      map(h.toLowerCase) = map(h.toLowerCase) + 1
      
    }
    for(h<-hh){
      //不可变的映射
      if(!map.keySet.contains(h.toLowerCase)) {
        val newMap = map3 + (h.toLowerCase->1)
        map3 = newMap

      }
      else{
        val newMap = map3 + (h.toLowerCase->map3(h.toLowerCase()+1))
        map3 = newMap

      }

    }


    for ((k, v) <- map3) {

      println(k, v)

    }
  }


  /** ************************* 元组 **********************************/
  /** ************************* 元组 **********************************/

  /*
*
* 元组
* */
  def test4(): Unit = {
    val t1 = (1, "2", 3.14, true)
    val v1 = t1._1
    // val v2 = t1 _2
    println(t1)
    println(v1)

    //val (1,"2",3,_) = t1 //匹配异常
    val (frist, second, _, _) = t1

    //partition生成对偶
    val hh = "Haha Xehe".partition(_.isUpper)
    println(hh)

    //拉链操作
    val arr1 = Array(1, "2", 3.14)
    val arr2 = Array("A", "2", 3.14)
    val t2 = arr1.zip(arr2)
    for ((s, n) <- t2) {
      println(s, n)
    }
    //键的集合.zip值的集合.toMap成一个映射
    val m1 = t2.toMap
    for ((k, v) <- m1) {
      println(k, v)

    }
  }


  /** *************************映射 **********************************/
  /** *************************映射 **********************************/

  /*
 *
 * 迭代
 * */
  def test3(): Unit = {
    val map1 = Map("k1" -> 10, "k2" -> 20, "k3" -> 30)

    for ((k, v) <- map1) {

      printf(" key : %s, value : %d ", k, v)

    }

    for (v <- map1.values) {
      println()
      printf(" value :%d", v)

    }

    for (k <- map1.keys) {
      println()
      printf("Key :%s", k)
    }

    for (k <- map1.keySet) {
      println()
      printf("key :%s", k)
    }

    for ((k, v) <- map1) yield (v, k)

  }

  /*
  *
  * 增删改
  * */
  def test2(): Unit = {
    /*
    *   操作可修改的映射
    * */
    val map2 = scala.collection.mutable.Map("k1" -> "10", "k2" -> 20, "k3" -> 30, "k4" -> 40)

    //修改
    map2("k1") = 11

    //增加
    map2("k6") = 60

    //增改
    map2 += ("k5" -> 50, "k2" -> 22)

    //删
    map2 -= "k3"
    map2 -= "k100"
    //println(map2)

    /*
    * 操作不可修改的映射<><><>只能把老的数据修改赋值给新的映射
    * */
    val map1 = Map("k1" -> 10, "k2" -> 20, "k3" -> 30)
    val newMap1 = map1 + ("k1" -> 11, "k4" -> 40)
    val newMap2 = map1 - "k2"
    //val map1 = map1-"k2"
    println(map1)
    println(newMap1)
    println(newMap2)


  }


  def test1(): Unit = {

    /*
    *  def -> [B](y: B): Tuple2[A, B] = Tuple2(self, y)
    *  A ->B 操作符用来创建对偶的("Alice",10)
    * */

    //构建映射  不可变 后面的k1覆盖前面的k1
    val map1 = Map("k1" -> 10, "k2" -> 20, "k1" -> 30)


    //可变
    val map2 = scala.collection.mutable.Map("k1" -> "10", "k2" -> 20, "k1" -> 30)

    //空映射
    val map3 = scala.collection.mutable.HashMap.apply()

    //映射是一种特别的元组
    val map4 = Map(("k1", 10), ("k2", 20), ("k3", 30))

    val map1K1 = map1("k1") //类似JAVA的map.get("k1")
    println(map1K1)
    println(map1)
    println(map3)
    println(map4)

    val map1K12 = if (map1.contains("k12")) map1("k12") else 0
    val map1K13 = map1.getOrElse("k12", 0)
    //    println(map1K12)
    //    println(map1K13)
  }

}
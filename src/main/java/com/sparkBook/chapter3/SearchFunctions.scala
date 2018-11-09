package com.sparkBook.chapter3

import org.apache.spark.rdd.RDD


class SearchFunctions(val query: String) {

  def isMatch(s: String): Boolean = {

    s.contains(query)

  }

  def getMatchesFunctionReference(rdd: RDD[String]): RDD[String] = {
    rdd.filter(isMatch)
  }

  def getMatchesFieldReference(rdd: RDD[String]): RDD[String] = {
    // 问题:"query"表示"this.query"，因此我们要传递整个"this"
    rdd.flatMap(x => x.split(query))
  }

  def getMatchesNoReference(rdd: RDD[String]): RDD[String] = {
    // 安全:只把我们需要的字段拿出来放入局部变量中
    val query_ = this.query
    rdd.flatMap(x => x.split(query))
  }

}

object SearchFunctions {

  def main(args: Array[String]): Unit = {
    val search = new SearchFunctions("ERROR")
    println(search.isMatch("我是1"))
  }
}






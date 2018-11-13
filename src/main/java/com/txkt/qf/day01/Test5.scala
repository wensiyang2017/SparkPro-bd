package com.txkt.qf.day01

class Test5 {

}

/*
*  字符串
* */
object Test5 {


  def main(args: Array[String]): Unit = {
    test3
  }

  /*
  * 格式化字符串
  * */
  private def test3(): Unit = {
    val floatVar = 12.456
    val intVar = 2000
    val stringVar = "菜鸟教程!"

    printf("字符串是%s,folat是%f,int是%d",stringVar,floatVar,intVar)

  }


  private def test2 = {
    var palindrome = "www.runoob.com"
    val len = palindrome.length
    println("String Length is : " + len)

  }

  private def test1 = {
    var str1 = "str1"
    var str2: String = "str2"
    str2 += "事实上"
    val str3 = new String("123");

    val buf1 = new StringBuilder();
    buf1 += '1'
    buf1 ++= "2"
    buf1 ++= "3"

    val buf2 = new StringBuilder;
    buf2 += '1'
    println(buf1)
    println(buf2)
    println(str1)
    println(str2)
    println(str3)
  }

}
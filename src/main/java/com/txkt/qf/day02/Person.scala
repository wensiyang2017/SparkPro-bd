package com.txkt.qf.day02

import scala.collection.mutable.ArrayBuffer
import scala.io.Source


/** ******摘选自博客 https://blog.csdn.net/Oeljeklaus/article/details/80558840 ************/


object hhh {

  def main(args: Array[String]): Unit = {
    //val p1 = new Person() Person被私有构造器了
    val p1 = Person.apply()
    //println(p1.id)
  }
}


/** *************************单例模式 ************************************************/
/** *************************单例模式 ************************************************/

/**
  *
  * Scala 中没有静态方法和静态字段,可以使用object这个语法来达到同样的目的
  *
  * 1.存放工具方法和常量
  *
  * 2.高效共享单个不可变的实例
  *
  * 3.单例模式
  *
  **/


object SingleToDemo {


  def main(args: Array[String]): Unit = {
    val session = SessionFactory.getSession()
  }
}

object SessionFactory {

  //相当于java的静态代码块
  var counts = 5
  val sessions = new ArrayBuffer[Session]()
  while (counts > 0) {
    sessions += new Session
    counts -= 1
  }

  def getSession(): Session = {
    sessions.remove(0)
  }
}

class Session {
}


/** *************************类 ************************************************/
/** *************************类 ************************************************/

/**
  *
  * private[] 指名只能再hust及其子包中访问
  * 后面的private私有化构造器
  **/
private[day02] class Person private {

  //编译出来的是一个final修饰的变量
  val id = "5927"

  var name: String = "华安"

  //定义一个年龄,这里使用private来修饰,只能再伴生对象中访问
  private var age: Int = 19

  //可以使用下划线对变量不初始化
  private var Class: String = _

  //只能在本类中被使用，伴生对象也不能被使用
  private[this] val province: String = "湖北"

  //给外界提供访问这个变量的接口
  def getProvince: String = {
    province
  }


  //在创建对象的时候,需要执行类里面的代码,执行伴生对象的对象
  println(Person.word)
  //读取文件后进行打印文件内容，只要使用构造器将会执行代码
  try {
    val content = Source.fromFile("/Users/wensiyang/Documents/随手记/date.txt").toString()
    println(content)

  }
  catch {
    case e: Exception => e.printStackTrace()

  } finally {
    println("finally")
  }
}

/**
  *
  * person的绊生对象,这是一个单例对象，
  * 可以和Person类共享变量，
  * 里面的方法和变量全部都是static
  *
  */
object Person {

  //如果使用Person()获取，将会执行apply方法，如果有多个参数将会对应具体的apply方法
  def apply(): Unit = {
    println("执行apply方法")
  }

  val word: String = "hello,scala"

  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 3, 4)
    val list2 = List(3, 4, 5, 6)
    //计算两个list的并集
    println(list1.union(list2))
    //计算两个list的交集
    println(list1.intersect(list2))
    //计算两个list的差集
    println(list1.diff(list2))
    //使用并行的计算和值
    println(list1.par.reduce(_ + _))
    val p = new Person
    println(p.id + ":" + p.name)
    //不允许改变
    //p.id="sdfas"
    //改变名字
    p.name = "唐伯虎"
    println(p.id + ":" + p.name)
    //改变年龄
    p.age = 22
    println(p.age)
    println(p.getProvince)

    val t = Person()
    val s = new Student("liming", 18, "1")
    println(s.id)
    println(s.Class)

  }

}

/**
  * 这里主构造器将会和类在一起，可以给默认的值，
  * 对于val的默认值，可以在构造器中修改
  *
  **/

class Student(private val name: String, private var age: Int, val id: String, val Class: String = "01") {

  //添加辅助的构造器,辅助构造器一定要先调用主构造器
//  def this() {
//    //this(name, age, id, Class)
//  }

}
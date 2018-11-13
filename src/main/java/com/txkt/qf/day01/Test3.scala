package com.txkt.qf.day01

class Test3 {

}

/*
* 方法,函数  Scala 中使用 val 语句可以定义函数，def 语句定义方法
*
* Scala 中的方法跟 Java 的类似，方法是组成类的一部分。所有的操作符+*-/都是方法
* Scala 中的函数则是一个完整的对象，Scala 中的函数其实就是继承了 Trait 的类的对象。
* */
object Test3 {

  def main(args: Array[String]): Unit = {
    //调用方法
    m1(3, 4)

    //调用函数
    f2(111111) //直接调用

    //被方法调用
    print(m2(f1))
    m3(f2)
    m4(f3)

  }

  /*
  *   scala里面方法和函数是不一样的
  *
  *   方法是def开头
  * */
  //def 方法名(参数列表):返回值 = {函数体}
  def m1(x: Int, y: Int): Unit = {
    print("我是方法" + x * y)
  }

  /*
  *   scala函数
  *   函数是val开头
  *   函数可以作为参数作为方法的参数
  * */
  // val 函数变量名称 = (参数列表) => 函数体
  val f1 = (x: Int, y: Int) => x * y

  val f2 = (x: Int) => {

    print("我是函数" + x)

  }

  val f3=()=>{
    print("我的天")
  }

  //定义一个方法
  //方法 m1 参数要求是一个函数，函数的参数必须是两个Int类型
  //\=>后面的类型是函数的返回类型,\:后面的是该方法的返回值类型
  def m2(f:(Int,Int) => Int) : Int = {
    f(11,6)
  }
  //函数的参数是1个Int类型
  def m3(f:Int=>Unit):Unit={
    f(4)
  }
  def m4(f:()=>Unit):Unit={
    f()
  }

  /*
  *
  *
  * */
  //def map[B, That](f: A => B)(implicit bf: CanBuildFrom[Repr, B, That]): That = {}

}

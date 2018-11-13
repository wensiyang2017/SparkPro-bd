package com.txkt.qf.day02

class test1 {

}

object test1 {

  def main(args: Array[String]): Unit = {

    /*
    *  调用无参数方法可以不要圆括号
    * */
    val myCounter = new Counter() //new Counter
    println(myCounter.inCreament())
    println(myCounter.current())

    val pt = new Point(10, 10)
    //pt.move(10, 10)

    val location = new Location(10,10,10)
    //location.move(10, 10)
    //location.move(10, 10,10)
  }

}


/**
  * 继承
  */

class Location( val xc:Int, val yc:Int,val zc:Int) extends Point(xc,yc){

  var z: Int = zc

  def move(dx: Int, dy: Int, dz: Int) {
    x = x + dx
    y = y + dy
    z = z + dz
    println ("x 的坐标点 : " + x);
    println ("y 的坐标点 : " + y);
    println ("z 的坐标点 : " + z);
  }

  override def move(dx: Int, dy: Int): Unit = {

    x += dx/2
    y += dy/2
    println("我复写了 x 的坐标点: " + x)
    println("我复写了 y 的坐标点: " + y)

  }


}

/**
  * 简单类和无参数方法
  */
class Point(xc: Int, yc: Int) {
  var x = xc
  var y = yc

  def move(dx: Int, dy: Int): Unit = {
    x += dx
    y += dy
    println("x 的坐标点: " + x)
    println("y 的坐标点: " + y)
  }


}

/**
  * 简单类和无参数方法
  **/
class Counter {

  private var value = 0

  //方法是默认公开的
  def inCreament(): Unit = {
    value += 1
  }

  def current() = value
}

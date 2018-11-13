package com.txkt.qf.day01

class Test9 {

}


/**
  *
  * Seq 序列(有先后顺序) 包含数组/列表
  * Set 一组没有顺序的任意元素
  * Map 一组(键,值)对偶
  **/
object Test9 {


  def main(args: Array[String]): Unit = {
    test4()

  }


  /** ************************* 练习 **********************************/
  /** ************************* 练习 **********************************/
  def test4(): Unit = {
    val l = List(1, 2, 3, 4, 5)
    val l1 = l.map(_ * 2)
   // println(l1)
    val l2 = l.filter(_ > 2)
    //println(l2)

    //collect 相当于先filter在map
    val l3 = l.collect { case i if i > 2 => i * 2 }
   // println(l3)


    val ll = List(List(1, 2, 3), List(4, 5, 6), Seq(7, 8, 9))
    val ll1 = ll.flatten
    //println(ll1)
    val ll2 = ll.flatMap(x=>x.map(_*2))
    //println(ll2)


    /******************************************/


    val s = List(jilen, shaw, yison)
    //一: 获取学生姓名的列表
    val nameList = s.map(_.name)
    val it = nameList.iterator
    while (it.hasNext) println(it.next())
    //二：我们要得到所有性别是男（m）的学生所组成的列表
    val nanSex = s.filter(_.sex=="m").map(_.name)
    val nanSex2 = s.collect{case s if s.sex=="m" =>s}
    println(nanSex)
    println(nanSex2)

   //三：现在我们要得到每个学生在列表中的位置以方便给他们编号
    println(s.zipWithIndex)


  }


  /** ************************* 集合 **********************************/
  /** ************************* 集合 **********************************/
  def test3(): Unit = {
    val set = Set(1, 23, 45)
    val set1 = set + 1

  }


  /** ************************* 列表 **********************************/
  /** ************************* 列表 **********************************/


  /*
  * 可变列表
  * */
  def test2(): Unit = {

    var list = scala.collection.mutable.ListBuffer(1, -1, 3, 4, 6)
    println(list)

    //添加
    list += 1
    println(list)

  }

  /*
  *  不可变列表
  * */
  def test(): Unit = {
    val digits = List(4, 2)

    val newDigits = 9 :: digits

    val it = newDigits.iterator
    while (it.hasNext) {
      println(it.next())
    }

    for (i <- newDigits) {
      println(i)
    }
  }

  def sum1(lst: List[Int]): Int = {
    if (lst == Nil) 0 else lst.head + sum1(lst.tail)
    //if (lst==Nil) 0 else  sum(lst) //todo 没有计算结果
  }

  //  def sum(lst:List[Int]):Int = {
  //    case Nil => 0
  //    case h::t => h + sum(t)
  //  }

  case class Student(
                      name: String,
                      age: Int,
                      sex: String
                    )
  val jilen = Student(
    name = "Jilen",
    age = 30,
    sex = "m"
  )
  val shaw = Student(
    name = "Shaw",
    age = 18,
    sex = "m"
  )
  val yison = Student(
    name = "Yison",
    age = 40,
    sex = "f"
  )
}
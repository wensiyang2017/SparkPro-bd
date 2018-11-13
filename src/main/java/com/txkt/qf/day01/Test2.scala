package com.txkt.qf.day01

class Test2 {
}

object Test2 {

  /*
*   for循环
*
* */
  def main(args: Array[String]): Unit = {
    print(1 to 10)
    print(1.to(10))
    println(1.until(10))

    // <- 把循环的结果赋值给左边的
    for (i <- 1 to 10) {
      //println("iiiii"+i)

    }

    val arr = Array("java", "scala", "C#")
    arr(0)
    for (j <- arr) println("I Love " + j)


    for (k <- 1 to 3; l <- 1 to 3 if (k != l)) println(k * 10 + l + "")


    // 把每次封装的对象放在集合中
    val res = for (i <- 1 until 10) yield i
    print(res) //Vector(1, 2, 3, 4, 5, 6, 7, 8, 9)


  }


  def test(): Unit = {

    //一: 普通for
    for (i <- 1 to 2) {
      println("iiiiiii" + i)
    }
    val arr = Array("1", "2", "3", "4")
    for (i <- arr) {
      println("arr[i]" + i)
    }

    //for(int i = 0 ;i<arr.length;i++ )
    //for(String str in list)

    //二:双重for循环
    for (i <- 1 to 5; j <- 1 until (6) if (i != j)) {
      //  1  ,2 3 4 5

      println(i * 10 + j + "")
    }

    //for封装出集合
    val res = 1 to 10
    println(res)

    val res2 = for (i <- 1 to 12) yield i
    print(res2)


  }


}
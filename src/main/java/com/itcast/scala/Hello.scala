import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Hello {

  def main(args: Array[String]): Unit = {
    println("-----main开始-----")
    //mapTest
    //funTest


    //val arr = Array("I love three things in this world", "Sun moon and you", "Sun for morning", "Moon for night", "You forever")
    //wordCountSingle(arr)

    avgSingle


  }

  /**
    * Scala单机avg
    **/
  def avgSingle(): Unit = {
    val td1 = Array(("bj",20.5),("sh",30.9),("hf",25.7),("sz",28.4))  //周一
    val td2 = Array(("bj",21.0),("sh",28.5),("hf",31.0))  //周二
    val td3 = Array(("bj",18.5),("sh",31.3),("hf",30.2),("sz",22.4))  //周三
    val td = td1 ++ td2 ++ td3
    val keyMap= td.groupBy(_._1)  //Map(String,Array(String,Int))
    // val avgtd= keyMap.map(t=>(t._1,t._2.map(_._2).sum/t._2.length)); 方案一

    // val avgtd = keyMap.map(x=>(x._1,x._2.reduce(_._2 + _._2)/x._2.length)) 方案二数组里面的元组好像不能用reduce
    val avgtd = keyMap.map(x=>(x._1,x._2.foldLeft(0.0)(_+_._2)/x._2.length))
    for (elem <- avgtd) {
      println(elem._1,elem._2)
    }
    for ((k,v) <- avgtd) {
      println(k,v)
    }
  }


  /**
    * Scala单机wordCount
    **/
  def wordCountSingle(lines: Array[String]): Unit = {
    //val line = lines.map(_.split(" "))
    //结果是Array(Array(I,love,three,things,in,this,world),Array(xxx))
    //数组里面包含数组(split切割字符串得到数组)
    //val word =  line.flatten

    // ==> 上面两个合并 Array[String](a,b,c,d)
    val words = lines.flatMap(_.split(" "))

    // ==> Array[String,Int]((a,1),(b,1))
    //val wordOne = words.map(x => (x, 1))
    val wordOne = words.map((_, 1))

    // ==> 相等的key聚合Map[String,Array[(String, Int)]] = Map(for -> A
    //rray((for,1), (for,1)), this -> Array((this,1)))
    //第一个_表示元组, ._1表示该元组的第一个元素
    wordOne.groupBy(_._1).map(t => (t._1, t._2.length))
    wordOne.groupBy(_._1).map(t => (t._1, t._2.map(_._2).sum))


  }

  /**
    * Scala重要函数
    **/
  def funTest() = {

    //reduce函数
    val arr1 = Array(1, 2, 3, 4, 5)
    println("reduce--" + arr1.reduce(_ + _)) // ((((1+2)+3)+4)+5)
    println("reduce--" + arr1.reduce(_ * _))
    //foldLeft 给个基数 从左边开始加
    println("foldLeft--" + arr1.foldLeft(1)(_ + _)) //(((((1+1)+2)+3)+4)+5)
  }


  /**
    * 映射(相当于java的map)
    **/
  def mapTest() = {
    val m1 = Map("k1" -> 1, "K2" -> 2)
    val m2 = Map(("k1", 1), ("k2", 2), ("k3", 3)) //多个对偶元组
    println(m2("k1"))


    //k v对调(for + yield)
    val yieldMap = for ((k, v) <- m2) yield (v, k)
    println(yieldMap)
    //k v对调(元组 (k,v) <==> 一个元组 )
    //将一个对偶元祖赋值给m
    val tupleMap = m2.map(m => (m._2, m._1))
    val t1 = m2.map(_._2)
    println(tupleMap)
    println(t1)


  }

  /**
    * 数组
    * java三种方式
    * 1 int[] arr = new Int[10];
    * 2 String[] strs = new String[]{"2","23"}
    * 3 int[] arr = {1,2,3,4,5,6,76,7}
    **/
  private def arrayTest(arr2: Array[Int]) = {
    //定长数组
    val arr1 = new Array[Int](10)
    val arr2 = Array(1, 2)
    arr2.map(x => x + 1)
    arr2.map(_ + 1)
    arr2.map(_ > 2)
    arr2.filter(_ % 2 == 0)
    arr2.reduce(_ + _)

    //变长数组
    val arr3 = ArrayBuffer[Int]()
    val arr4 = new ArrayBuffer[Int]()
    val arr5 = new ArrayBuffer()

    //数组常用算法
    println("---------------------------")
    println(arr2.sum)
    println(arr2.min)
    println(arr2.sorted)
    println(arr2.sortWith(_ > _))
    println(arr2.count(_ > 2))
  }

  def testScala(args: Array[String]): Unit = {
    //val 相当于final修饰不可变,常用节约资源
    val a = "abc" // 自己会推断出类型
    val b: String = "abcd" //定义好类型
    //    b = "123" 不可以修改了

    val x, y = 100 //可以同时定义多个
    var c = "ccc"
    c = "ccccc"

    //没有加减乘除,都是方法
    val i = 1
    val j = 3
    i.+(j) // 解释: 对象(i),调用(.),方法(+),参数(j)  ==>a.add(b)

    /*
    * for
    * */

    //for循环
    //for (x <- 1 to 10) println(x) //1<= <=10
    //for (x <- 1 until 10) println(x) //  1<= <10


    /*
    * 函数
    * */
    //定义函数 def fun(变量名称:参数类型):返回值类型(普通函数可以不以返回值,单递归函数必要) = 函数体
    def fun(x: Int): Int = x + 123

    def udf1(x: Int, y: Int): Int = x + y

    def udf2(x: Int, y: Int): Int = {
      x + y
    }

    //参数也可以先给个默认值
    def udf3(x: Int, y: Int = 100) = a + b

    //可变参数
    def udf4(args: Int*) = {
      var result = 0 //还是不要带;了可以运行不了
      for (i <- args) result += i
      result
    }

    //无返回值的
    def udf5(x: Int) {
      print(x)
    } //函数体前面的=去了 这种情况下一点要{}
    def udf6(x: Int): Unit = {
      print(x)
    } //返回值设置为Unit

    //匿名函数
    (x: Int) =>
      x + 111

      //前方高能类似JS,定义一个匿名函数赋值给一个变量 var hh = new function(){}
      val f = (x: Int) => x + 100
      //匿名函数应用x=>x*2
      1.to(10).map(x => x * 2)


      //懒值
      val words = Source.fromFile("D:/随记录/备忘录.txt").mkString

      //变量首次使用时取值
      lazy val lazyWords = Source.fromFile("D:\\随记录\\备忘录.txt").mkString


      val arr2 = Array(1, 2)
      this.arrayTest(arr2)

  }

}
package com.txkt.qf.day02



/**
  *
  * 伴生对象
  *
  * */
class ApplyFunc  private(id :Long,name:String,initialAge:Int){

  private var age = initialAge

  var newName = name

}

object ApplyFunc{ //伴生对象

  def apply(id: Long, name: String, initialAge: Int): ApplyFunc = new ApplyFunc(id, name, initialAge)


}

object Hello{

  def main(args: Array[String]): Unit = {
    val applyFunc = ApplyFunc(1L,"xiaoming",29)
    println(applyFunc.newName)
  }

}
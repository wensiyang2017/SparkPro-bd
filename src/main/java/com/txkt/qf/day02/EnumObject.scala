package com.txkt.qf.day02


/**
  *
  * 枚举
  * scala没有提供枚举类型,提供了Enumeration助手类
  *
  **/
class EnumObject {

}

object EnumObject {


  def main(args: Array[String]): Unit = {
    println(StatusEnum.CREATE.id)
    println(StatusEnum.SUCCESS)
    println(StatusEnum.getById(100).id)
  }

  /*
  *
  * 定义一个状态的枚举
  *
  * */
  object StatusEnum extends Enumeration {
    /*
    *
    * CREATE(1,"创建"),
    * SUCCESS(2,"成功"),
    * CANCEL(3,"取消"),
    * FAIL(4,"失败");
    *
    * */
    type StatusEnum = Value //声明枚举对外暴露变量类型

    //定义具体的枚举实例 id,name 不传递id默认0++
    val CREATE = Value("成功") //创建
    val SUCCESS = Value(1) //成功
    val CANCEL = Value(2, "取消") //取消
    val CREFAILATE = Value("失败") //失败


    def getById(code: Int): StatusEnum.Value = {

      StatusEnum.values.foreach(statusEnum => (
        if (statusEnum.id.equals(code)) {
          return statusEnum
        }
        )
      )
      return null
    }

  }


}

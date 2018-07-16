package com.sparkBook.chapter3

/**
  * 宽依赖和窄依赖
  */
object DependenciesDemo {

  /**
    * 宽依赖: 子Rdd的每个分区依赖父Rdd的所有分区(groupBykey 1 对 n)
    * 窄依赖: 子Rdd的每个分区依赖常数个父Rdd分区(map,union 1 对 1)
    **/

}

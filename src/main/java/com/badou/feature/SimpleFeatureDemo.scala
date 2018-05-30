package com.badou.feature

import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object SimpleFeatureDemo {
  def main(args: Array[String]): Unit = {
    //https://blog.csdn.net/u013063153/article/details/54615378
    /**
      * 在2.0版本之前，使用Spark必须先创建SparkConf和SparkContext，代码如下：
      * //设置spark配置并创建上下文
      * val sparkConf = new SparkConf().setAppName("example").setMaster("Local")
      * val sc = new SparkContext(sparkConf)
      * //让spark访问sql
      * val spark = new org.apache.spark.sql.SQLContext(sc)
      *
      * Spark2.0中只要创建一个SparkSession就够了，
      * SparkConf、SparkContext和SQLContext都已经被封装在SparkSession当中。
      *
      * val spark = SparkSession
      * .builder()
      * .appName("SparkSessionZipsExample")
      * .config("spark.sql.warehouse.dir", warehouseLocation)
      * .enableHiveSupport()
      * .getOrCreate()
      *
      * // spark 设置运行参数
      *     spark.conf.set("spark.sql.shuffle.partitions", 6)
      *     spark.conf.set("spark.executor.memory", "2g")
      *
      **/

    val spark = SparkSession
      .builder()
      .appName("Produc_LR")
      .enableHiveSupport()
      .getOrCreate()

    //order_id,product_id,add_to_cart_order(加入购物车顺序),reordered(是否再次购买)
    val priors = spark.sql("select * from badou.priors")

    //order_id,user_id,
    // eval_set：prior历史行为，train训练（test中user已经购买了的其中一个商品），
    //test（最终我们要预测的数据集，包含哪个用户他可能会购买的商品）
    //order_number:这个user订单的编号，体现订单的先后顺序
    //order_dow:(day of week)，订单在星期几
    //order_hour_of_day：一天中的哪个小时（分成24小时）
    //days_since_prior_order：order_number后面一个订单与前面一个订单相隔天数（注意第一个订单没有）
    val orders = spark.sql("select * from badou.orders")
    this.Feat(priors, orders)
  }

  def Feat(priors: DataFrame, orders: DataFrame): DataFrame = {
    /**
      * Product Feature
      * 1 .销量 pro_cnt
      * 2 .再次购买量prod_sum_rod,再次购买比例prod_rod_rate
      *
      **/
    //我们这个priors没有k-v形式,所以不能用groupByKey
    val productCnt = priors.groupBy("product_id").count()
    //商品再次购买总数和比例
    val productRodCnt = priors.selectExpr("product_id", "cast(reordered as int)").groupBy("product_id").agg(sum("reordered") as ("prod_sum_rod"), avg("reordered") as ("prod_rod_rate"))

    //将上面两个join
    val productFeat = productCnt.join(productRodCnt, "product_id").selectExpr("product_id",
      "count as prod_cnt",
      "prod_sum_rod",
      "prod_rod_rate")

    /**
      * user Features：
      * 1.每个用户平均购买订单的间隔周期 user_avg_day_gap
      * 2.每个用户的总订单数量
      * 3.每个用户购买的product商品去重后的集合数据
      * 4.每个用户总商品数量以及去重后的商品数量总商品数量
      * 5.每个用户购买的平均每个订单的商品数量
      * 6. user
      */
    //处理订单中days_since_prior_order为null的列
    val ordersNew = orders.selectExpr("*", "if(days_since_prior_order='',0,days_since_prior_order) as dspo").drop("days_since_prior_order")
    /*
    *  每个用户购买订单的间隔周期
    * */
    val userGap = ordersNew.groupBy("user_id").agg(avg("dspo") as ("user_avg_day_gap"))
    // val userGap = ordersNew.selectExpr("user_id","cast(dspo as int)").groupBy("user_id").avg("dspo").withColumnRenamed("avg(dspo)","user_avg_day_gap")
    /*
    * 每个用户的订单总数
    * */
    val orderCnt = ordersNew.groupBy("user_id").count()

    /*
      * 每个用户购买的product商品去重后的集合数据
      */
    //用户和商品的fd
    val op = orders.join(priors, "order_id").select("user_id", "product_id")
    //将product_id聚合在一起(转为为Rdd,groupBykey把mapValue聚合去重)
    import priors.sparkSession.implicits._
    val userUniOrdRecs = op.rdd.map(x => (x(0).toString, x(1).toString)).groupByKey().mapValues(_.toSet.mkString(",")).toDF("user_id", "product_records")

    /*
     * 每个用户总商品数量以及去重后的商品数量总商品数量
     */
    val productCnt = op.groupBy("user_id").count()
    val userProRcdSize = op.rdd.map(x => (x(0).toString, x(1).toString))
      .groupByKey()
      .mapValues { x =>
        val rs = x.toSet
        (rs.size, rs.mkString(","))
      }
      .toDF("user_id", "tuple")
      .selectExpr("user_id", "tuple._1 as prod_dist_cnt", "tuple._2 as prod_records")


    /*
    *  每个用户购买的平均每个订单的商品数量
    * */
    val uop = orders.join(priors, "order_id").select("user_id", "order_id","product_id")
     uop.groupBy("order_id","user_id").count()
  }
}

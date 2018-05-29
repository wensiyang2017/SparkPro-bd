import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{avg, sum}

object JiebaDemo {
  def Feat(priors:DataFrame,orders:DataFrame): DataFrame ={
    val productCnt = priors.groupBy("product_id").count()
    val productRodCnt = priors.selectExpr("product_id", "cast(reordered as int)")
      .groupBy("product_id")
      .agg(sum("reordered").as("prod_sum_rod") , avg("reordered").as("prod_rod_rate"))

    val productFeat = productCnt.join(productRodCnt,"product_id")
      .selectExpr("product_id",
        "count as prod_cnt",
        "prod_sum_rod",
        "prod_rod_rate")
    val ordersNew =orders
      .selectExpr("*","if(days_since_prior_order='',0,days_since_prior_order) as dspo")
      .drop("days_since_prior_order")
    val userGap = ordersNew.selectExpr("user_id","cast(dspo as int)")
      .groupBy("user_id")
      .avg("dspo")
      .withColumnRenamed("avg(dspo)","user_avg_day_gap")
    val userOrdCnt = orders.groupBy("user_id").count()
    val op = orders.join(priors,"order_id").select("user_id","product_id")
    import priors.sparkSession.implicits._
    val userUniOrdRecs=op.rdd.map(x=>(x(0).toString,x(1).toString))
      .groupByKey()
      .mapValues(_.toSet.mkString(","))
      .toDF("user_id","product_records")
    val userProRcdSize=op.rdd.map(x=>(x(0).toString,x(1).toString)).groupByKey().mapValues{record=>
      val rs = record.toSet
      (rs.size,rs.mkString(","))
    }.toDF("user_id","tuple")
      .selectExpr("user_id","tuple._1 as prod_dist_cnt","tuple._2 as prod_records")
    val ordProCnt = priors.groupBy("order_id").count()
    val userPerOrdProdCnt = orders.join(ordProCnt,"order_id")
      .groupBy("user_id")
      .avg("count")
      .withColumnRenamed("avg(count)","user_avg_ord_prods")
    val userFeat = userGap.join(userOrdCnt,"user_id")
      .join(userProRcdSize,"user_id")
      .join(userPerOrdProdCnt,"user_id")
      .selectExpr("user_id",
        "user_avg_day_gap",
        "count as user_ord_cnt",
        "prod_dist_cnt as user_prod_dist_cnt",
        "prod_records as user_prod_records",
        "user_avg_ord_prods")
    orders.join(priors,"order_id")
      .select("user_id","product_id","eval_set")
      .join(productFeat,"product_id")
      .join(userFeat,"user_id")
  }
}

package com.recommend

import breeze.numerics.{pow, sqrt}
import org.apache.spark.sql.DataFrame

/**
  *   根据用户行为推荐(协同过滤)
  */
object UserCF {

  def userSim(df:DataFrame):DataFrame ={
    import  df.sparkSession.implicits._

    val userScoreSum = df.rdd.map(x=>(x(0).toString,x(2).toString))
      .groupByKey()
      .mapValues(x=>sqrt(x.toArray.map(r=>pow(r.toDouble,2)).sum))
      .toDF("user_id","rating_sqrt_sum")
    return  userScoreSum
  }

}

package io.prophecy.pipelines.test.graph

import io.prophecy.libs._
import io.prophecy.pipelines.test.config.ConfigStore._
import io.prophecy.pipelines.test.config.Context
import io.prophecy.pipelines.test.udfs.UDFs._
import io.prophecy.pipelines.test.udfs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object Script_0 {
  def apply(context: Context): Unit = {
    val spark = context.spark
    val Config = context.config
    val x = 1
    val y = 2
  }

}

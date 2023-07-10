package io.prophecy.pipelines.test.graph

import io.prophecy.libs._
import io.prophecy.pipelines.test.udfs.UDFs._
import io.prophecy.pipelines.test.config.Context
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object Reformat_2 {

  def apply(context: Context, in: DataFrame): DataFrame =
    in.select(expr("string_char(str_col, 1)").as("test"),
              string_char(col("str_col"), lit(1)).as("other_test")
    )

}

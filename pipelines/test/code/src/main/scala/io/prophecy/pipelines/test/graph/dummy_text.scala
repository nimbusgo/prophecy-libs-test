package io.prophecy.pipelines.test.graph

import io.prophecy.libs._
import io.prophecy.pipelines.test.config.Context
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object dummy_text {

  def apply(context: Context): DataFrame =
    context.spark.read
      .format("text")
      .schema(StructType(Array(StructField("value", StringType, true))))
      .load("asdf")

}

package io.prophecy.pipelines.test_config.graph.Subgraph_1

import io.prophecy.libs._
import io.prophecy.pipelines.test_config.udfs.PipelineInitCode._
import io.prophecy.pipelines.test_config.udfs.UDFs._
import io.prophecy.pipelines.test_config.graph.Subgraph_1.config.Context
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object Reformat_1_1 {

  def apply(context: Context, in: DataFrame): DataFrame =
    in.select(lit(context.config.p1).as("p1"))

}

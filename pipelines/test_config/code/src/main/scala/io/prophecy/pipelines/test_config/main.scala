package io.prophecy.pipelines.test_config

import io.prophecy.libs._
import io.prophecy.pipelines.test_config.config.Context
import io.prophecy.pipelines.test_config.config._
import io.prophecy.pipelines.test_config.udfs.UDFs._
import io.prophecy.pipelines.test_config.udfs._
import io.prophecy.pipelines.test_config.udfs.PipelineInitCode._
import io.prophecy.pipelines.test_config.graph._
import io.prophecy.pipelines.test_config.graph.Subgraph_1
import io.prophecy.pipelines.test_config.graph.Subgraph_1.config.{
  Context => Subgraph_1_Context
}
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object Main {

  def apply(context: Context): Unit = {
    val df_SQLStatement_0 = SQLStatement_0(context)
    val df_Subgraph_1 = Subgraph_1.apply(
      Subgraph_1_Context(context.spark, context.config.Subgraph_1),
      df_SQLStatement_0
    )
    val df_Reformat_1 = Reformat_1(context, df_SQLStatement_0)
  }

  def main(args: Array[String]): Unit = {
    val config = ConfigurationFactoryImpl.getConfig(args)
    val spark: SparkSession = SparkSession
      .builder()
      .appName("Prophecy Pipeline")
      .config("spark.default.parallelism",             "4")
      .config("spark.sql.legacy.allowUntypedScalaUDF", "true")
      .enableHiveSupport()
      .getOrCreate()
      .newSession()
    val context = Context(spark, config)
    spark.conf.set("prophecy.metadata.pipeline.uri", "pipelines/test_config")
    registerUDFs(spark)
    MetricsCollector.start(spark, "pipelines/test_config")
    apply(context)
    MetricsCollector.end(spark)
  }

}

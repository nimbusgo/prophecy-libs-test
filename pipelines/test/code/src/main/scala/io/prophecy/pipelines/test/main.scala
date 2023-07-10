package io.prophecy.pipelines.test

import io.prophecy.libs._
import io.prophecy.pipelines.test.config.Context
import io.prophecy.pipelines.test.config._
import io.prophecy.pipelines.test.udfs.UDFs._
import io.prophecy.pipelines.test.udfs._
import io.prophecy.pipelines.test.graph._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.apache.spark.sql.expressions._
import java.time._

object Main {

  def apply(context: Context): Unit = {
    Script_0(context)
    val df_dummy_text = dummy_text(context)
    val df_Reformat_1 = Reformat_1(context, df_dummy_text)
    val df_Reformat_2 = Reformat_2(context, df_Reformat_1)
  }

  def main(args: Array[String]): Unit = {
    val config = ConfigurationFactoryImpl.fromCLI(args)
    val spark: SparkSession = SparkSession
      .builder()
      .appName("Prophecy Pipeline")
      .config("spark.default.parallelism",             "4")
      .config("spark.sql.legacy.allowUntypedScalaUDF", "true")
      .enableHiveSupport()
      .getOrCreate()
      .newSession()
    val context = Context(spark, config)
    spark.conf.set("prophecy.metadata.pipeline.uri", "pipelines/test")
    registerUDFs(spark)
    MetricsCollector.start(spark, "pipelines/test")
    apply(context)
    MetricsCollector.end(spark)
  }

}

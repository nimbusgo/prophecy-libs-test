package io.prophecy.pipelines.test.config

import org.apache.spark.sql.SparkSession
case class Context(spark: SparkSession, config: Config)

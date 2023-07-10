package io.prophecy.pipelines.test_config.graph.Subgraph_1.config

import io.prophecy.libs._
import pureconfig._
import pureconfig.generic.ProductHint
import org.apache.spark.sql.SparkSession

object Config {

  implicit val confHint: ProductHint[Config] =
    ProductHint[Config](ConfigFieldMapping(CamelCase, CamelCase))

}

case class Config(p1: String = "$p2") extends ConfigBase
case class Context(spark: SparkSession, config: Config)

package io.prophecy.pipelines.test_config.config

import pureconfig._
import pureconfig.generic.ProductHint
import io.prophecy.libs._
import io.prophecy.pipelines.test_config.graph.Subgraph_1.config.{
  Config => Subgraph_1_Config
}

case class Config(
  p2:         String = "abc",
  Subgraph_1: Subgraph_1_Config = Subgraph_1_Config(),
  db_secret:  DatabricksSecret = DatabricksSecret(scope = "scope", key = "key"),
  map_param:  Map_param = Map_param(),
  y:          Int = 5,
  x:          String = "$p2"
) extends ConfigBase

object Map_param {

  implicit val confHint: ProductHint[Map_param] =
    ProductHint[Map_param](ConfigFieldMapping(CamelCase, CamelCase))

}

case class Map_param(key1: String = "x", key2: String = "y")

object DatabricksSecret {

  implicit val myIntReader: ConfigReader[DatabricksSecret] =
    ConfigReader[String].map { s =>
      val Array(scope, key) = s.split(":")
      DatabricksSecret(scope, key)
    }

}

case class DatabricksSecret(scope: String, key: String) {

  override def toString: String = {
    import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
    dbutils.secrets.get(scope = scope, key = key)
  }

}

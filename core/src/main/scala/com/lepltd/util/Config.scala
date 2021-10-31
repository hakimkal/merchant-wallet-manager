package com.lepltd
package util

import com.typesafe.config.{ ConfigFactory, ConfigValue }

object Config {
  lazy val config = ConfigFactory.load()
  config.checkValid(ConfigFactory.defaultReference)

  val host: String = config.getString("mwManager.app.host")
  val port: Int    = config.getInt("mwManager.app.port")

  val postgresDbUser: String     = config.getString("mwManager.postgres.user")
  val postgresDbPassword: String = config.getString("mwManager.postgres.password")
  val postgresDbName: String     = config.getString("mwManager.postgres.database")

}

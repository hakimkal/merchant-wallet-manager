package com.lepltd
package util

import com.typesafe.config.{ ConfigFactory, ConfigValue }

object Config {
  lazy val config = ConfigFactory.load()
  config.checkValid(ConfigFactory.defaultReference)

  val host: String = config.getString("owlet.app.host")
  val port: Int    = config.getInt("owlet.app.port")

  val postgresDbUser: String     = config.getString("owlet.postgres.user")
  val postgresDbPassword: String = config.getString("owlet.postgres.password")
  val postgresDbName: String     = config.getString("owlet.postgres.database")

  val secretKeys              = config.getString("owlet.jwt.secret-key")
  val merchantTokenExpiration = config.getInt("owlet.jwt.expiration")
}

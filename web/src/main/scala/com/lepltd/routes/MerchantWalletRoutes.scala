package com.lepltd.web
package routes

import akka._
import akka.actor.typed.scaladsl.AskPattern.{ schedulerFromActorSystem, Askable }
import akka.actor.typed.{ ActorRef, ActorSystem }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout

import org.slf4j.Logger

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.DurationInt
import scala.util.{ Failure, Success }

class MerchantWalletRoutes()(implicit system: ActorSystem[_]) {
  implicit val timeout: Timeout = 25.seconds
  lazy val log: Logger          = system.log

  implicit val ec: ExecutionContextExecutor = system.executionContext

  // lazy val emailServiceRoutes: Route = None

}

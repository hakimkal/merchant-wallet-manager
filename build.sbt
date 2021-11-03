name := "merchant-wallet-manager"
lazy val akkaVersion     = "2.6.17"
lazy val akkaHttpVersion = "10.2.6"

lazy val sharedSettings = Seq(
  organization := "com.lepltd",
  version := "1.0.0",
  scalaVersion := "2.13.6",
  trapExit := false,
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked"
  )
)

lazy val merchantWalletManager = (project in file("."))
  .aggregate(core, merchant, web)
  .settings(
    assembly / mainClass := Some("com.lepltd.web.Main")
  )

lazy val web = (project in file("web"))
  .dependsOn(core, merchant)
  .settings(
    sharedSettings,
    mainClass in assembly := Some("com.lepltd.web.Main")
  )

lazy val core = (project in file("core")).settings(
  sharedSettings,
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
    "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.5",
    "io.spray" %% "spray-json" % "1.3.6",
    "org.scalatest" %% "scalatest" % "3.1.0" % Test,
    "org.tpolecat" %% "doobie-core" % "0.12.1",
    "org.tpolecat" %% "doobie-hikari" % "0.12.1", // HikariCP transactor.
    "org.tpolecat" %% "doobie-postgres" % "0.12.1", // Postgres driver 42.2.19 + type mappings.
    "org.tpolecat" %% "doobie-quill" % "0.12.1", // Support for Quill 3.6.1
    "org.tpolecat" %% "doobie-specs2" % "0.12.1" % "test", // Specs2 support for typechecking statements.
    "org.tpolecat" %% "doobie-scalatest" % "0.12.1" % "test" // ScalaTest support for typechecking statements.

  )
)

lazy val merchant = (project in file("merchant"))
  .dependsOn(core)
  .settings(
    sharedSettings,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.2.2" % Test
    )
  )

resolvers += "Sonatype release repository" at "https://oss.sonatype.org/content/repositories/releases/"

Compile / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint"
)

// show full stack traces and test case durations
Test / testOptions += Tests.Argument("-oDF")
Test / logBuffered := false

ThisBuild / assemblyMergeStrategy := {
  case PathList("module-info.class") =>
    MergeStrategy.discard
  case PathList("io.netty.versions.properties") =>
    MergeStrategy.discard
  case x if x.endsWith("/module-info.class") || x.endsWith("/io.netty.versions.properties") =>
    MergeStrategy.discard
  case "reference.conf" => MergeStrategy.concat
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)

}

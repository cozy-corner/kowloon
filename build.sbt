import sbt.Keys.libraryDependencies

import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.6.3" // latest version of Scala 3 at 202502
ThisBuild / organization := "com.kowloon"

lazy val commonSettings = Seq(
    scalacOptions ++= Seq(
        "-deprecation",
        "-feature",
        "-language:higherKinds",
        "-Ykind-projector:underscores"
    ),
    libraryDependencies ++= Seq(
        "org.typelevel" %% "cats-effect" % "3.5.7",
        "org.typelevel" %% "mouse" % "1.3.2",
        "org.scalatest" %% "scalatest" % "3.2.19" % Test
    )
)

lazy val core = project
  .in(file("core"))
  .settings(
      name := "kowloon-core",
      libraryDependencies ++= Seq(
          "org.typelevel" %% "cats-core" % "2.13.0",
          "io.circe" %% "circe-core" % "0.14.10",
          "io.github.iltotore" %% "iron" % "2.6.0"  // Refined types
      )
  )
  .settings(commonSettings)

lazy val application = project
  .in(file("modules/application"))
  .dependsOn(core)
  .settings(commonSettings)

lazy val api = project
  .in(file("modules/api"))
  .dependsOn(application)
  .settings(
        libraryDependencies ++= Seq(
        "org.http4s" %% "http4s-ember-server" % "0.23.30",
        "org.http4s" %% "http4s-circe" % "0.23.30",
        "org.http4s" %% "http4s-dsl" % "0.23.30",
        "ch.qos.logback" % "logback-classic" % "1.5.16"
      )
  ).settings(commonSettings)

lazy val infrastructure = project
  .in(file("modules/infrastructure"))
  .dependsOn(core)
  .settings(
      name := "kowloon-infra",
      libraryDependencies ++= Seq(
//          "org.tpolecat" %% "skunk-core" % "0.6.4",
          "com.github.jwt-scala" %% "jwt-circe" % "10.0.4"
      )
  )

lazy val root = project
  .in(file("."))
  .aggregate(core, api, infrastructure)

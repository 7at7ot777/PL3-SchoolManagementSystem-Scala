ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "PL3Project"
  )
libraryDependencies += "com.typesafe.akka" %% "akka-persistence-typed" % "2.8.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.7"

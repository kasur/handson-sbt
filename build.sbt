import sbt.Keys._

name := "HolaActor"

version := "1.0"

scalaVersion := "2.11.6"

mainClass in (Compile, run) := Some("com.exadel.erusak.init.HolaActor")

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Repository Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT",
  "com.typesafe.akka" %% "akka-cluster" % "2.4-SNAPSHOT",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4-SNAPSHOT",
  "com.typesafe.akka" %% "akka-testkit" % "2.4-SNAPSHOT",

  "com.softwaremill.macwire" %% "macros" % "1.0.1",

  "com.softwaremill.macwire" %% "runtime" % "1.0.1",

  "org.scalatest" %% "scalatest" % "2.2.4" % "test",

  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.7",
  "-deprecation", "-feature", "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint"
)

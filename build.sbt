import sbt.Keys._

//name := "HolaActor"

version := "1.0"

scalaVersion := "2.11.6"

//mainClass in (Compile, run) := Some("com.exadel.erusak.init.HolaActor")

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Akka Snap Repo" at "http://repo.akka.io//snapshots/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % Versions.Akka,
  "com.typesafe.akka" %% "akka-cluster" % Versions.Akka,
  "com.typesafe.akka" %% "akka-slf4j" % Versions.Akka,
  "com.typesafe.akka" %% "akka-testkit" % Versions.Akka,
  "com.typesafe.akka" %% "akka-stream-experimental" % Versions.Akka_Stream,

  "com.softwaremill.macwire" %% "macros" % Versions.Macwire,

  "com.softwaremill.macwire" %% "runtime" % Versions.Macwire,

  "org.scalatest" %% "scalatest" % Versions.Scalatest % "test",

  "ch.qos.logback" % "logback-classic" % Versions.Logback
)

scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.7",
  "-deprecation", "-feature", "-unchecked",
  "-Xlog-reflective-calls",
  "-Xlint"
)


fork in run := true
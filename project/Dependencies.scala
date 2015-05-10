import sbt._


object Dependencies {

  import Versions._


  val resolutiionRepos = Seq(
    "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
  )

  val akka_actor = "com.typesafe.akka" %% "akka-actor" % Akka
  val akka_cluster = "com.typesafe.akka" %% "akka-cluster" % Akka
  val akka_slf4j = "com.typesafe.akka" %% "akka-slf4j" % Akka
  val logback = "ch.qos.logback" % "logback-classic" % Logback

  val macwire_macros = "com.softwaremill.macwire" %% "macros" % Macwire
  val macwire_runtime = "com.softwaremill.macwire" %% "runtime" % Macwire

  val akka_stream = "com.typesafe.akka" %% "akka-stream-experimental" % Akka_Stream

  val testkit = "com.typesafe.akka" %% "akka-testkit" % Akka
  val scalatest = "org.scalatest" %% "scalatest" % Scalatest

  val all_deps = Seq(akka_actor, akka_cluster, akka_slf4j, logback, testkit % "test", macwire_macros, macwire_runtime, akka_stream, scalatest % "test")

}
package com.exadel.erusak.core.streams

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl.Source
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author kasured.
 */
class BasicTransformationTest extends FlatSpec with Matchers{

  implicit val actorSystem  = ActorSystem("actorSystem")
  implicit val materializer = ActorFlowMaterializer()

  implicit val dispatcher = actorSystem.dispatcher

  val text =
    """One, two, Freddy's coming for you
      | Three, four, Better lock your door
      | Five, six, grab a crucifix
      | Seven, eight, Gonna stay up late
      | Nine, ten, Never sleep again...
    """.stripMargin

  "Basic source processing" should "complete without errors" in {

    Source(() => text.split("\\s").iterator)
      .map(_.toUpperCase)
      .runForeach(println)
      .onComplete(_ => actorSystem.terminate())

  }

}




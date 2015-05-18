package com.exadel.erusak.core.streams

import akka.actor.ActorSystem
import akka.stream.ActorFlowMaterializer
import akka.stream.scaladsl._
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author kasured.
 */
class BasicTransformationTest extends FlatSpec with Matchers{

  behavior of "Multiple sources -> flow -> Sink should be expected"

  implicit val actorSystem  = ActorSystem("actorSystem")
  implicit val materializer = ActorFlowMaterializer()

  implicit val dispatcher = actorSystem.dispatcher

  it should "slow down for the slow consumer" in {

    import FlowGraph.Implicits._

    val upper: Source[Int, Unit] = Source(() => Iterator from 0).take(10)
    val lower: Source[Int, Unit] = Source(() => Iterator from 11).take(11)

    val source  = Source[(Int, Int)]() { implicit b =>
      val zip = b.add(Zip[Int, Int]())

      upper ~> zip.in0
      lower ~> zip.in1

      zip.out

    }

    val flow = Flow[(Int, Int)] map { case (x, y) => s"merging $x and $y to get sum ${x+y}" }
    val sink = Sink.foreach(println)

    val future = source.via(flow).runWith(sink)

  }

}

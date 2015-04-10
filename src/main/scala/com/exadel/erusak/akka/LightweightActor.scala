package com.exadel.erusak.akka

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, Props}
import akka.event.Logging
import akka.util.Timeout

import akka.pattern.{ask, pipe}
import com.exadel.erusak.akka.BaseActor.{ActorD, ActorS, ActorI}
import com.exadel.erusak.akka.LightweightActor.Processor
import com.exadel.erusak.init.KickOff

import scala.concurrent.Future

import scala.concurrent.duration._

case class Result(i: Int, s: String, d: Double)
case object Request
/**
 *
 * Created by erusak on Aug 20 2014.
 */
class LightweightActor extends Actor {

  import context.dispatcher
  import scala.language.postfixOps

  implicit val timeout = Timeout(5 seconds)

  val logger = Logging(context.system, this)

  val actorI = context.actorOf(Props[ActorI], "actor-I")
  val actorS = context.actorOf(Props[ActorS], "actor-S")
  val actorD = context.actorOf(Props[ActorD], "actor-D")

  val processor = context.actorOf(Props[Processor], "processor")

  override def receive: Actor.Receive = {
    case KickOff =>
      kickOff()
  }

  private def kickOff(): Unit = {
    //this is very important to construct these guys first before passing to for yield
    val iF = ask(actorI, Request)
    val sF = actorS ask Request
    val dF = actorD ? Request

    val future: Future[Result] =
      for {
        i <- iF.mapTo[Int]
        s <- sF.mapTo[String]
        d <- dF.mapTo[Double]
      } yield Result(i,s,d)

    logger.info("Piping to processor")
    future pipeTo processor
  }

}

object LightweightActor {
  class Processor extends Actor {
    val logger = Logging(context.system, this)
    override def receive: Receive = {
      case Result(x, y, z) =>
        logger.info("Ok I received this guy {} And trying really hard to process it", Result(x,y,z))
        TimeUnit.SECONDS.sleep(10)
    }
  }
}


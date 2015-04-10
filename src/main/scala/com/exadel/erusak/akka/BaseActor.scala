package com.exadel.erusak.akka

import java.util.concurrent.TimeUnit

import akka.actor.Actor
import akka.event.Logging

/**
 *
 * Created by erusak on Sep 02 2014.
 */

abstract class BaseActor[T] extends Actor {
  val logger = Logging(context.system, this)

  override def receive: Receive = {
    case Request =>
      logger.info("Ok. now i am gonna think for a while to produce...")
      sender() ! produce()
      logger.info("Okay I am done")
    case any =>
      logger.info("I do not know how to process this {}", any)
  }

  def produce(): T
}

object BaseActor {
  class ActorI extends BaseActor[Int] {
    override def produce(): Int = {
      TimeUnit.SECONDS.sleep(5)

      val result = Int.MaxValue

      logger.info("... {}", result)
      result
    }
  }

  class ActorS extends BaseActor[String] {
    override def produce(): String = {
      TimeUnit.SECONDS.sleep(2)

      val result = "Blabla"
      logger.info("... {}", result)
      //here you go
      result
    }
  }

  class ActorD extends BaseActor[Double] {
    override def produce(): Double = {
      TimeUnit.SECONDS.sleep(1)

      val result = Double.MaxValue
      logger.info("... {}", result)
      //here you go
      result
    }
  }
}
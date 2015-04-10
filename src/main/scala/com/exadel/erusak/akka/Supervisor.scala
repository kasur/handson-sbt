package com.exadel.erusak.akka

import akka.actor.SupervisorStrategy.{Escalate, Stop, Restart, Resume}
import akka.actor.{Actor, OneForOneStrategy, Props}
import akka.event.Logging

/**
 *
 * @author erusak.
 */
class Supervisor extends Actor {

  import scala.concurrent.duration._
  import scala.language.postfixOps

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
    case _: ArithmeticException       => Resume
    case _: NullPointerException      => Restart
    case _: IllegalArgumentException  => Stop
    case _: Exception                 => Escalate
  }

  override def receive = {
    case p: Props => sender() ! context.actorOf(p)
  }

}

class Child extends Actor {
  val logger = Logging(context.system, this)
  var state = 0
  override def receive = {
    case ex: Exception    => throw ex
    case i: Int           => state = i
    case "get"            => sender() ! state
  }
}

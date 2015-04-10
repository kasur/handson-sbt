package com.exadel.erusak.akka.barista

import akka.actor.SupervisorStrategy.{Resume, Directive}
import akka.actor.{OneForOneStrategy, SupervisorStrategy, Actor, Props}
import akka.util.Timeout
import com.exadel.erusak.akka.barista.Barista.EspressoCup.Filled
import com.exadel.erusak.akka.barista.Barista.{ClosingTime, EspressoCup}
import com.exadel.erusak.akka.barista.Exceptions.PaperJamException
import com.exadel.erusak.akka.barista.Protocol.EspressoRequest
import com.exadel.erusak.akka.barista.Register.{Espresso, Transaction}

/**
 * @author erusak.
 */
object Barista {

  case object ClosingTime

  case class EspressoCup(state: EspressoCup.State)

  object EspressoCup {
    sealed trait State
    case object Clean extends State
    case object Filled extends State
    case object Dirty extends State
  }

  case class Receipt(amount: Int)
}

class Barista extends Actor {

  import akka.pattern.{ask, pipe}
  import scala.concurrent.duration._
  import context.dispatcher
  import scala.language.postfixOps

  implicit val timeout = Timeout( 4 seconds )

  val register = context.actorOf(Props[Register], "Register")

  val decider: PartialFunction[Throwable, Directive] = {
    case _:PaperJamException => Resume
  }


  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    decider.orElse(SupervisorStrategy.defaultStrategy.decider)
  }

  override def receive: Receive = {
    case EspressoRequest =>
      val receipt = register ? Transaction(Espresso)

      receipt map((EspressoCup(Filled), _)) pipeTo sender

    case ClosingTime => context.stop(self)
  }



}

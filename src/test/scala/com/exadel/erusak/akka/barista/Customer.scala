package com.exadel.erusak.akka.barista

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.exadel.erusak.akka.barista.Barista.EspressoCup.Filled
import com.exadel.erusak.akka.barista.Barista.{EspressoCup, Receipt}
import com.exadel.erusak.akka.barista.Customer.{Waiting, WantCoffee, Happy, CaffeineWithdrawalWarning}
import com.exadel.erusak.akka.barista.Protocol.{StatusRequest, EspressoRequest}

/**
 * @author erusak.
 */
object Customer {
  case object CaffeineWithdrawalWarning

  sealed trait Status
  case object Happy extends Status
  case object WantCoffee extends Status
  case object Waiting extends Status
}

class Customer(coffeeSource: ActorRef) extends Actor with ActorLogging {
  import context._

  override def receive: Receive = {
    case CaffeineWithdrawalWarning =>
      coffeeSource ! EspressoRequest
      become(waiting)
    case StatusRequest => sender() ! WantCoffee
  }

  def happy: Receive = {
    case CaffeineWithdrawalWarning =>
      log.info(s"Oh man I am fine really. Maybe a bit later")
    case StatusRequest => sender() ! Happy
  }

  def waiting: Receive = {
    case (EspressoCup(Filled), Receipt(amount)) =>
      log.info(s"Fucking yeah!!! I have got my caffeine for just $amount")
      become(happy)
    case StatusRequest => sender() ! Waiting
  }

}
package com.exadel.erusak.akka.barista

import akka.actor.{ActorLogging, Actor}
import com.exadel.erusak.akka.barista.Barista.Receipt
import com.exadel.erusak.akka.barista.Exceptions.PaperJamException
import com.exadel.erusak.akka.barista.Register.{Article, Cappuccino, Espresso, Transaction}

import scala.util.Random

/**
 * @author erusak.
 */
object Register {
  sealed trait Article
  case object Espresso extends Article
  case object Cappuccino extends Article

  case class Transaction(article: Article)
}

class Register extends Actor with ActorLogging {
  var revenue = 0
  val prices = Map[Article, Int](Espresso -> 177, Cappuccino -> 189)

  def makeReceipt(amount: Int): Receipt = {
    if(Random.nextBoolean()) throw new PaperJamException("Fuck!! Again jammed the paper")
    Receipt(amount)
  }

  override def receive: Receive = {
    case Transaction(article) =>
      val price = prices(article)

      //replying to sender
      sender ! makeReceipt(price)

      revenue += price

      log.info(s"Revenue changed to be $revenue")
  }

  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = super.preStart()

  @scala.throws[Exception](classOf[Exception])
  override def postStop(): Unit = super.postStop()

  @scala.throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = super.preRestart(reason, message)

  @scala.throws[Exception](classOf[Exception])
  override def postRestart(reason: Throwable): Unit = {
    super.postRestart(reason)
    log.warning(s"Ok fellas I have replaced the jammed papper and now ready. BTW, exception was ${reason.getMessage}")
    log.info(s"And the revenue is $revenue")
  }
}
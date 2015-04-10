package com.exadel.erusak.akka.barista

import java.util.concurrent.{Executors, TimeUnit}

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKitBase}
import com.exadel.erusak.akka.barista.Customer.{CaffeineWithdrawalWarning, Waiting, WantCoffee}
import com.exadel.erusak.akka.barista.Protocol.StatusRequest
import com.typesafe.config.ConfigFactory
import org.scalatest.{ShouldMatchers, WordSpecLike}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

/**
 * @author erusak.
 */
class BaristaTestSuite extends TestKitBase with WordSpecLike with ImplicitSender with ShouldMatchers {

  implicit lazy val system = ActorSystem("CoffeeHouse", ConfigFactory.parseString("""
      akka.loggers = ["akka.testkit.TestEventListener"]
  """))

  "All customers should get their caffeine request processed" in {
    val barista = system.actorOf(Props[Barista], "Barista")
    val evgeny = system.actorOf(Props(classOf[Customer], barista), name = "EvgenyRusak")
    val raman = system.actorOf(Props(classOf[Customer], barista), name = "RamanRusak")


    /*simpleCustomerRoundUpTest(evgeny)
    simpleCustomerRoundUpTest(raman)*/

    /*implicit val executionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))
    Future {
      var generated = 0
      val itr = stream(barista).iterator
      while(!Thread.currentThread().isInterrupted && itr.hasNext) {
        simpleCustomerRoundUpTest(itr.next())
        generated += 1
        TimeUnit.MILLISECONDS.sleep(500)
      }
      println(s"Number of actors generated $generated")
    }*/

    val itr = stream(barista).iterator
    for(i <- 1 to 10) {
      simpleCustomerRoundUpTest(itr.next())
    }


    TimeUnit.SECONDS.sleep(10)

  }

  def simpleCustomerRoundUpTest(customer: ActorRef): Unit = {
    customer ! StatusRequest
    expectMsg(WantCoffee)

    customer ! CaffeineWithdrawalWarning
    customer ! StatusRequest
    expectMsg(Waiting)
  }

  def stream(coffeeSource: ActorRef): Stream[ActorRef] = {
    def newCustomer(): ActorRef = {system.actorOf(Props(classOf[Customer], coffeeSource), name = s"Customer_${Random.nextInt(1000)}")}
    def loop(customer: ActorRef): Stream[ActorRef] = customer #:: loop (newCustomer())
    loop(newCustomer())
  }

}

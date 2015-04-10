package com.exadel.erusak.akka

import akka.actor.{ActorRef, ActorSystem, Props, Terminated}
import akka.testkit.{EventFilter, ImplicitSender, TestKitBase}
import com.typesafe.config.ConfigFactory
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 *
 * @author erusak.
 */
class FaultHandlingSpec extends TestKitBase with WordSpecLike with MustMatchers with ImplicitSender {

  implicit lazy val system = ActorSystem("testsystem", ConfigFactory.parseString("""
      akka.loggers = ["akka.testkit.TestEventListener"]
    """))

  "A supervisor" must {
    "apply a chosen strategy to its child" in {

      val supervisor = system.actorOf(Props[Supervisor], "supervisor")

      supervisor ! Props[Child]
      val child = expectMsgType[ActorRef]

      EventFilter.warning(occurrences = 1) intercept {
        child ! 17
        child ! "get"
        expectMsg(17)

        child ! new ArithmeticException
        child ! "get"
        expectMsg(17)
      }

      EventFilter[NullPointerException](occurrences = 1) intercept {
        child ! new NullPointerException
        child ! "get"
        expectMsg(0)
      }

      EventFilter[IllegalArgumentException](occurrences = 1) intercept {
        //registering for terminated events
        watch(child)
        child ! new IllegalArgumentException //stop in that case

        expectMsgPF() { case Terminated(`child`) => println(s"actor reported stop $child")}
      }

    }
  }

}

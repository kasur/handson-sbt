package com.exadel.erusak.core.futures

import java.util.concurrent.TimeUnit.SECONDS

import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.concurrent.ScalaFutures._
import org.scalatest.time._

import scala.util.{Failure, Success}

import SpanSugar._

/**
 * @author erusak.
 */
class CoffeeMakerSuite extends FlatSpec with ShouldMatchers with Eventually {

  "Following these steps we" should "have a cappuccino" in {
    whenReady(prepareCappuccino(), timeout(10 seconds)) { result =>
      result should be ("cappuccino")
    }
  }

  "Following these steps we" should "have a cappuccino or expected error message" in {

    val futureResult = prepareCappuccinoF()

    val failInFuture = futureResult.failed

    futureResult onComplete {
      case Success(cappuccino) =>
        println("here is your cappuccino sir")
        cappuccino should be ("cappuccino")
      case Failure(ex) =>
        println(s" There is no fucking cappuccino sir here is that crap instead ${ex.getMessage}")
        ex.getMessage should include ("taking too long to brew")
    }

    //yak
    SECONDS.sleep(15)
  }

}

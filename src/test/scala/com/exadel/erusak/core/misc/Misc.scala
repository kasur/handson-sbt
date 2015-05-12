package com.exadel.erusak.core.misc

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author kasured.
 */
class Misc extends FlatSpec with Matchers {

  object Singleton {
    def aa = "aa"
  }

  val o1 = Singleton
  val o2 = Singleton

  o1 eq o2 should be (right = true)

  def lambda = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  def mult(f: (Int) => Int): (Int) => Int = {
    (x: Int) => 7 * f(x)
  }

  lambda(5) should be (6)
  mult(lambda) {5} should be (42)

  val map = Map("one" -> 1, "two" -> 2, "three" -> 3)
  map("one") should be (1)
  map get "two" shouldBe Some(2)
  map get "four" shouldBe None

  val anotherMap = Map("one" -> 1, 1 -> 2) //type inference happens here cause we o not provide it explicitly

  val variable = "one"

  val matchResult: (String) => String = {
    case `variable` => "match"
    case _ => "oops"
  }

  matchResult("one") should be ("match")
  matchResult("two") shouldBe "oops"

}

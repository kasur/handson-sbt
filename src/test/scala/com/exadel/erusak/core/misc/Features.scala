package com.exadel.erusak.core.misc

import org.scalatest.{Matchers, FeatureSpec, GivenWhenThen}

/**
 * @author kasured.
 */
class Features extends FeatureSpec with GivenWhenThen with Matchers {

  info {
    """Exploring core features of Scala language.
      |Contains various features and scenarios.
    """.stripMargin
  }
  feature("Partial functions") {
    scenario("Case statements are the PartialFunction syntactic sugar") {

      Given("The following definition of partial function")
      val even: PartialFunction[Int, String] = {
        case x if x % 2 == 0 => s"$x is even"
      }
      val ring: PartialFunction[Int, String] = even orElse { case x => s"$x is odd"}

      Then("this function should cover all the domain of integers")
      (even isDefinedAt 10) shouldBe true
      (even isDefinedAt 11) shouldBe false
      (ring isDefinedAt 11) shouldBe true

      And("these tests should succeed")
      even(10) should be ("10 is even")
      ring(11) should be ("11 is odd")

    }
  }

}

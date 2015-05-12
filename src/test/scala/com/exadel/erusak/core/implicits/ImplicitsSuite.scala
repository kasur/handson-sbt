package com.exadel.erusak.core.implicits

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author kasured.
 */
class ImplicitsSuite extends FlatSpec with Matchers {

  behavior of "Implicits should be as expected"

  it should "take the implicit import and apply to the argument" in {

    import Implicits._

    11.isEven shouldBe false
    10.isOdd should be (false)

    17.isOdd shouldBe true
    12.isEven shouldBe true

  }


}

object Implicits {
  implicit class Evenness(val original: Int) {
    def isEven = original % 2 == 0
    def isOdd = !isEven
  }
}

package com.exadel.erusak.core.collections

import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * @author erusak.
 */
class CoreTestSuite extends FlatSpec with ShouldMatchers {
  "Collection reduce with given op" should "behave as expected against the elements of seq" in {
    val seq = Seq[Int](1, 2, 3, 4, 5)

    val op: (Int, Int) => Int = _ + _

    seq reduce op should be (15)

    seq reduce (_ * _) should be (120)

  }
}

package com.exadel.erusak.core.scalatest

import org.scalatest.{ShouldMatchers => Should, FlatSpec}

/**
 * @author erusak.
 */
class BDDSuite extends FlatSpec with Should {
  "An empty Set" should "have size 0" in {
    Set.empty.size should be (0)
  }

  it should "throw exception NoSuchElement if head method is called on it" in {
    an [NoSuchElementException] should be thrownBy {
      Set.empty.head
    }
  }
}

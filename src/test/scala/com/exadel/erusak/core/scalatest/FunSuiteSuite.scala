package com.exadel.erusak.core.scalatest

import org.scalatest.FunSuite

/**
 * @author erusak.
 */
class FunSuiteSuite extends FunSuite {

  //test to give a flavour of functional suite
  test("An empty set should have size 0 ") {
    assertResult(0) {
      Set.empty.size
    }
  }

  test("Calling head on an empty set should produce appropriate exception"){
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }

}

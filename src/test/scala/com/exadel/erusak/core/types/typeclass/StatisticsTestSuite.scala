package com.exadel.erusak.core.types.typeclass

import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * @author erusak.
 */
class StatisticsTestSuite extends FlatSpec with ShouldMatchers {
  "Testing statistics API" should "pass smoothly" in {

    val vectorOfDoubles = Vector[Double](1, 2, 3, 4, 5, 5.3)
    val vectorOfInts = Vector[Int](1, 2, 3, 4, 5, 7, 9)

    //mean
    println(Statistics.mean(vectorOfDoubles))

    //median
    println(Statistics.median(vectorOfInts))

    //quartiles
    println(Statistics.quartiles(vectorOfInts))
  }
}

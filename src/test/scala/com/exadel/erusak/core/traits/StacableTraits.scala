package com.exadel.erusak.core.traits

import org.scalatest.{ShouldMatchers, FlatSpec}


object StacableTraits {

}

/**
 * @author kasured.
 */
class StacableTraits extends FlatSpec with ShouldMatchers {

  "stacable traits printers" should "match cases" in {
    (new Printer with Capitalized print "me") should be ("printing Me")
    (new Printer with Capitalized with Suffixed print "me") should be ("printing Me with tail")
    (new Printer with Suffixed with Capitalized print "me") should be ("printing Me with tail")
    (new Printer with Capitalized with Prefixed print "me") should be ("printing With head me")
    (new Printer with Suffixed with Capitalized with Prefixed print "me") should be ("printing With head me with tail")
  }

}


class Printer {
  def print(str: String): String = {
    val result = s"printing $str"
    println(result)
    result
  }
}

trait Capitalized extends Printer {
  abstract override def print(str: String): String = {
    super.print(str.capitalize)
  }
}

trait Suffixed extends Printer {
  val suffix = " with tail"
  abstract override def print(str: String): String ={
    super.print(str concat suffix)
  }
}

trait Prefixed extends Printer {
  val prefix = "with head "
  abstract override def print(str: String): String ={
    super.print(prefix concat str)
  }
}
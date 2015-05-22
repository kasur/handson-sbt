package com.exadel.erusak.core.misc

import java.time.DayOfWeek

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

  feature("Named and Default arguments") {

    scenario("the order is not important with named arguments"){
      Given(s"person class with constructor arguments")
      case class Person(firstname: String, lastname: String)
      val `Rusak` = "Rusak"
      val `Evgeny` = "Evgeny"
      val person = Person(lastname = `Rusak`, firstname = `Evgeny`)
      Given(s"having the person $person")
      Then("One can define the object with different order of named arguments")
      person.firstname shouldBe `Evgeny`
      person.lastname shouldBe `Rusak`
    }

    scenario("One can also use the default function"){
      def opp[A](arg: A, f: (A, A) => A = (a1: A, a2: A) => a1): A = f(arg,arg)
      Given(s"function with the default function argument ${opp _}")

      Then("We can omit the function")
      opp(5) shouldBe 5

      And("we can specify the argument explicitly")
      opp[Int](5, _ * _ ) shouldBe 25
    }

    scenario("Manifest is used to deal with reification -> mainly to conform with Array creation") {
      //def name[A](implicit m: Manifest[A]) = m.toString()
      def name[A : Manifest] = implicitly[Manifest[A]].toString()
      println(name[Int => Int])
      name[Int => Int] shouldBe "scala.Function1[Int, Int]"

      def tryMe[T : Manifest](array: Array[T]) = Array(array(0))
      tryMe(Array(1,2)) should be (Array(1))

    }

    scenario("Extractors pattern match") {
      class Emp(val firstName: String, val middle: Option[String], val lastName: String)
      object Emp {
        def unapply(x: Emp): Option[(String, Option[String], String)] = Some((x.lastName, x.middle, x.firstName))
      }
      val emp1 = new Emp("First", None, "Last")
      val result = emp1 match {
        case Emp("First", None, x) => "ha"
        case Emp("First", Some(x), _) => "haha"
        case _ => "booo"
      }
      result should be ("booo")
    }

    scenario("Types, Variance etc") {

      class Classifier[+A : Manifest](val element: A) {

        private[this] val _elem = element
        private[this] val _runtime = implicitly[Manifest[A]]
        private[this] val _class = _runtime.runtimeClass


        def get = _elem

        def clazz = _class.getSimpleName

      }

      val fruit: Classifier[Fruit] = new Classifier[Orange](new Orange())
      fruit.clazz shouldBe "Orange"

    }

    scenario("Enumerations") {
      object DOW extends Enumeration {
        type DOW = Value
        val Monday,
            Tuesday,
            Wednesday,
            Thursday,
            Friday,
            Saturday,
            Sunday = Value
      }

      import DOW._

      def isWeekEnd(day: DOW) = day == Saturday || day == Sunday

      DOW.values filterNot isWeekEnd shouldBe Set(Monday, Tuesday, Wednesday, Thursday, Friday)

    }

  }

  abstract class Fruit
  class Orange extends Fruit

}

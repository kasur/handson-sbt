package com.exadel.erusak.core.option

import org.scalatest.{ShouldMatchers => Should, FlatSpec}

/**
 * @author erusak.
 */
class OptionSuite extends FlatSpec with Should {

  "Testing option" should "all pass" in {
    val userWithNone = User(1, "Yauhen", "Rusak", 31, Some("male"))
    val gender = userWithNone.gender match {
      case Some(gen) => gen
      case None => "n/a"
    }
    gender should be ("male")
  }

  "The test value" should "be increased by 1" in {
    //testing optioning with side effect - yak
    var init = 0
    UserRepository.findById(1).foreach(user => init += 1)
    init should be (1)

    val user = UserRepository.findById(1)

    user.flatMap(_.gender) should be (Some("male"))


    val result = for {
      user <- UserRepository.findAll
      gender <- user.gender
    } yield gender

    result should be (List("male"))

  }



  case class User (
    id: Int,
    firstName: String,
    lastName: String,
    age: Int,
    gender: Option[String]
  )

  object UserRepository {
    private val users = Map(
      1 -> User(1, "Yauhen", "Rusak", 30, Some("male")),
      2 -> User(2, "Evgeny", "Rusak", 31, None)
    )
    def findById(id: Int): Option[User] = users.get(id)
    def findAll = users.values
  }

}

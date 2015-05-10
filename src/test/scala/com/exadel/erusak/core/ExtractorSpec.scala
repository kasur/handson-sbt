package com.exadel.erusak.core

import org.scalatest._

/**
 * @author kasured.
 */
class ExtractorSpec extends FlatSpec with Matchers {

  "Extractor" should "behave as expected" in {

    ((new RegularUser("Evgeny"): User) match {
      case PremiumUser(name) => s"premium user $name"
      case RegularUser(name) => s"regular user $name"
    }) should be ("regular user Evgeny")

    ((new PremiumUser("Evgeny"): User) match {
      case PremiumUser(name) => s"premium user $name"
      case RegularUser(name) => s"regular user $name"
    }) should be ("premium user Evgeny")

  }


}

trait User {
  def name: String
}

final class RegularUser(val name: String) extends User
final class PremiumUser(val name: String) extends User

object RegularUser {
  def unapply(regularUser: RegularUser): Option[String] = Some(regularUser.name)
}

object PremiumUser {
  def unapply(premiumUser: PremiumUser): Option[String] = Some(premiumUser.name)
}
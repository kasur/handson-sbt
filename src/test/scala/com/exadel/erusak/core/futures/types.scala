package com.exadel.erusak.core.futures

/**
 * @author kasured.
 */
object types {

  case class Water(temperature: Int)

  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

}

package com.exadel.erusak.akka

/**
 * @author erusak.
 */
package object barista {
  object Protocol {

    sealed trait CustomerRequest
    case object EspressoRequest extends CustomerRequest

    sealed trait ServiceRequest
    case object StatusRequest extends ServiceRequest

  }
  object Exceptions {
    class PaperJamException(msg: String) extends Exception(msg)
  }
}

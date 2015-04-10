package com.exadel.erusak.core.types.typeclass

import scala.annotation.implicitNotFound

/**
 * @author erusak.
 */
object Math {

  @implicitNotFound("[No member of type class NumberLike in scope for ${T}]")
  trait NumberLike[T] {
    def plus(x: T, y: T): T
    def minus(x: T, y: T): T
    def divide(x: T, y: Int): T
  }

  implicit object NumberLikeInt extends NumberLike[Int] {
    override def plus(x: Int, y: Int): Int = x + y
    override def divide(x: Int, y: Int): Int = x / y
    override def minus(x: Int, y: Int): Int = x - y
  }

  implicit object NumberLikeDouble extends NumberLike[Double] {
    override def plus(x: Double, y: Double): Double = x + y
    override def divide(x: Double, y: Int): Double = x / y
    override def minus(x: Double, y: Double): Double = x - y
  }

}

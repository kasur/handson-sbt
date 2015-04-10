package com.exadel.erusak.core.types.typeclass

/**
 * @author erusak.
 */
object Statistics {
  import Math.NumberLike //used to import implicits into the scope

  def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T = {
    ev.divide(xs.reduce(ev.plus), xs.size)
  }

  //now using context bounds to avoid verbosity
  def median[T: NumberLike](xs: Vector[T]): T = {
    xs(xs.size / 2)
  }

  def quartiles[T: NumberLike](xs: Vector[T]): (T, T, T) = {
    val size: Int = xs.size
    (xs(size / 4), median(xs), xs(size / 4 * 3))
  }

}

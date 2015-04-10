package com.exadel.erusak.core

/**
 * @author erusak.
 */
package object dry {

  import scala.language.reflectiveCalls

  def withCloseable[T <: { def close(): Unit}, U](closeable: T)(operator: T => U): U = {
    val result = operator(closeable)
    closeable.close()
    result
  }

}

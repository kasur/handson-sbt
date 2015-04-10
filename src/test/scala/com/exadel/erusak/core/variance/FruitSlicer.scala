package com.exadel.erusak.core.variance

/**
 * @author erusak.
 */
class FruitSlicer[+T] {
  def slice[U >: T](fruit: U): U = fruit
}

object Mixer {
  val appleSlicer = new FruitSlicer[Apple]
  appleSlicer.slice(new Orange(1.1d))
}

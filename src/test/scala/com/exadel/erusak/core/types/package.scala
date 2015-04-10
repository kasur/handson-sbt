package com.exadel.erusak.core

/**
 * @author erusak.
 */
package object types {
  abstract class Food
  abstract class Animal {
    type SuitableFood <: Food
    def eat(food: SuitableFood)
  }
  
  
  case class Grass() extends Food
  class Cow extends Animal {
    type SuitableFood = Grass
    override def eat(food: Grass): Unit = println("I am eating fucking fresh grass")
  }
  
  
  case class Grasshoper() extends Food
  class Bird extends Animal {
    type SuitableFood = Grasshoper
    override def eat(food: Grasshoper): Unit = println("I am eating fucking fresh grasshoper")
  }
  
}

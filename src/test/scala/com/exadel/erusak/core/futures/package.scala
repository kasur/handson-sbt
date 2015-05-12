package com.exadel.erusak.core

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit.MILLISECONDS

import com.exadel.erusak.core.futures.types.Water

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Random, Try}

/**
 * @author erusak.
 */
package object futures {

  type CoffeeBeans = String
  type GroundCoffee = String

  type Milk = String
  type FrothedMilk = String

  type Espresso = String
  type Cappuccino = String

  def grind(beans: CoffeeBeans): GroundCoffee = {
    println("Start grinding....")
    s"ground coffee of $beans"
  }

  def grindF(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    println("Start grinding....")
    MILLISECONDS.sleep(Random.nextInt(2000))
    if (beans == "really bad beans") throw new IllegalArgumentException(s"these $beans beans really suck")
    println("Finished grinding...")
    s"ground coffee of $beans"
  }

  def heatWater(water: Water): Water = water.copy(temperature = 85)

  def heatWaterF(water: Water): Future[Water] = Future {
    println("Start boiling water...")

    MILLISECONDS.sleep(Random.nextInt(5000))

    if(water.temperature < 0)
      throw new IllegalArgumentException(s"this is a fucking ice man ${water.temperature} is too cold")

    println("Finished boiling water...")
    water.copy(temperature = 85)
  }

  def frothMilk(milk: Milk): FrothedMilk = s"frothed milk of $milk"

  def frothMilkF(milk: Milk): Future[FrothedMilk] = Future {

    println("Start frothing milk...")

    MILLISECONDS.sleep(Random.nextInt(4500))

    if("sour milk" == milk) throw new IllegalArgumentException("Milk is sour")

    println("Stop frothing milk...")
    s"frothed milk of $milk"
  }

  def brew(groundCoffee: GroundCoffee, heatedWater: Water): Espresso = "espresso"

  def brewF(groundCoffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {

    println("Start brewing coffee...")

    Random nextInt 15000 match {
      case i:Int if i > 10000 =>
        MILLISECONDS.sleep(Random.nextInt(5000))
        throw new IllegalArgumentException(s"taking too long to brew: $i millis")
      case i:Int =>
        MILLISECONDS.sleep(Random.nextInt(i))
    }

    println("Finished brewing coffee...")
    "espresso"
  }

  def combine(coffee: Espresso, milk: FrothedMilk): Cappuccino = "cappuccino"

  implicit val _context = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  def prepareCappuccino(): Future[Cappuccino] = {
    import FutureHelper._ //importing implicits to convert Try to Future
    for {
      ground <- grindF("really good beans")
      water <- Try(heatWater(Water(30)))
      frothedMilk <- Try(frothMilk("The best milk ever"))
      espresso <- Try(brew(ground, water))
    } yield combine(espresso, frothedMilk)
  }

  def prepareCappuccinoF(): Future[Cappuccino] = {

    val groundF = grindF("really good beans")
    val waterF = heatWaterF(Water(10))
    val frothedMilkF = frothMilkF("best milk ever")

    for {
      ground <- groundF
      water <- waterF
      frothedMilk <- frothedMilkF
      espresso <- brewF(ground, water)
    } yield combine(espresso, frothedMilk)
  }

}

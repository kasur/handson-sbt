package com.exadel.erusak.core.di

import com.exadel.erusak.di.Bundle.{NetworkModule, ConsoleModule}
import com.exadel.erusak.di.PrinterModule.Manager
import org.scalatest.FlatSpec

/**
 * @author erusak.
 */
class DITestSuite extends FlatSpec {

  class ConsoleManager extends Manager with ConsoleModule {
    type Document = String
  }

  class NetworkManager extends Manager with NetworkModule {
    type Document = Int
  }

  "DI" should "work as expected" in {
    val networkManager = new NetworkManager()
    val consoleManager = new ConsoleManager()

    networkManager.provideMaterials()
    consoleManager.provideMaterials()

  }
}

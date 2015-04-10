package com.exadel.erusak.di

import com.exadel.erusak.di.PrinterModule.{NetworkPrinter, ConsolePrinter}

/**
 * @author erusak.
 */
object Bundle {
  import com.softwaremill.macwire.MacwireMacros._
  trait ConsoleModule {
    lazy val printer = wire[ConsolePrinter]
  }
  trait NetworkModule {
    lazy val printer = wire[NetworkPrinter]
  }
}

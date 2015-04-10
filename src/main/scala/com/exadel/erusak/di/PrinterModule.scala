package com.exadel.erusak.di

/**
 * @author erusak.
 */
object PrinterModule {

  trait Printer {
    def print()
  }

  trait Manager {
    def printer: Printer
    type Document

    def provideMaterials(): Seq[Document] = {
      println("[Let me print it]")
      printer.print()
      println("[Ok I am done]")
      Seq.empty[Document]
    }
  }

  class ConsolePrinter extends Printer {
    override def print(): Unit = println("[Printing to Console]")
  }

  class NetworkPrinter extends Printer {
    override def print(): Unit = println("[Printing to Network]")
  }

}

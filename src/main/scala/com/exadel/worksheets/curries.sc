import java.io.PrintWriter
import java.nio.file.{Paths, Files}

def withPrintWriter(fileName: String)(operation: PrintWriter => Unit) = {
  assertFile(Files.notExists(Paths.get("/home/kasur/tmp/pw.out")))
  val writer = new PrintWriter(fileName)
  try {
    operation(writer)
  } finally {
    writer.close()
  }
}

def assertFile(precondition: => Boolean) = {
  if (precondition) true
  else throw new IllegalArgumentException
}

withPrintWriter("/home/kasur/tmp/pw.out") {
  writer => {
    writer.println("Hola!!")
  }
}
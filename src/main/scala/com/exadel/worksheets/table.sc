def makeRowSeq(row: Int): Seq[String] = {
  for (col <- 1 to 17) yield {
    val value = (col * row).toString
    val padding = " " * (4 - value.length)
    padding + value
  }
}

def makeRow(row: Int) = makeRowSeq(row).mkString

def table() = {
  val tableSeq = for (row <- 1 to 13) yield makeRow(row)
  tableSeq.mkString("\n")
}

println(table())
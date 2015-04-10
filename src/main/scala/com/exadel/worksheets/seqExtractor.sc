class Names;
object Names {
  def unapplySeq(name: String): Option[(String, String, Seq[String])] = {
    val names = name.trim.split(" ")
    if (names.length < 2) None
    else Some((names.last, names.head, names.drop(1).dropRight(1).toSeq))
  }
}
def greetings(name: String) = {
  val greeting = name match {
    case Names(lastName, firstName, _*) => s"Hola $firstName $lastName"
    case _ => "Hola! Make sure you have filled in your name."
  }
  greeting
}

greetings("Yauhen Ivanavich Junior Rusak");

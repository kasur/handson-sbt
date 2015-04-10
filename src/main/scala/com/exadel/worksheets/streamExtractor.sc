val xs = 17 #:: Stream.empty

val result = xs match {
  case first #:: second #:: _ => (first, second)
  case _ => ()
}



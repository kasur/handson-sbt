def While(p: => Boolean)(b: => Unit): Unit = {
  if(p) { b; While(p)(b)}
}

var i = 1
While(i <= 10) {
  println(i)
  i += 1
}

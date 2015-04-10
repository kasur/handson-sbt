val pairs: Seq[(String, Int)] = List(
  ("Peter", 13),
  ("John", 17),
  ("Mark", 23),
  ("Coolman", 29),
  ("Yauhen", 31)
);

val withFilter: ((String, Int)) => Boolean = { case (_, age) => age > 20 && age < 30 }

val withMapper: ((String, Int)) => String = { case (name, _) => name }

pairs filter withFilter map withMapper
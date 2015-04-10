trait User {
  def name: String
}

class FreeUser(
    val name: String,
    val score: Int,
    val prob: Double)
  extends User;
object FreeUser {
  def unapply(user: FreeUser): Option[(String, Int, Double)]
      = Some((user.name, user.score, user.prob))
  def promotionNote(user: FreeUser) = s"We are gonna promote you $user"
}
object FreeUserExtractor {
  def unapply(user: FreeUser): Boolean = user.prob > 0.70
}

class PremiumUser(val name: String, val score: Int) extends User
object PremiumUser {
  def unapply(user: PremiumUser): Option[(String,Int)]
      = Some((user.name, user.score))
}

def testExtractor() = {
  val user: User = new PremiumUser("Yauhen", 100)
  user match {
    case FreeUser(name, score, pb)
          => s"Hello, free user $name with score $score and prob $pb"
    case PremiumUser(name, _) => s"Hello, premium user $name"
  }
}

def anotherTestExtractor() = {
  val user: User = new FreeUser("Yauhen", 111, 0.75d)
  user match {
    case freeUser @ FreeUserExtractor()
          => FreeUser.promotionNote(freeUser)
    case _ => "We do not care"
  }
}

testExtractor()
anotherTestExtractor()
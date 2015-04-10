trait Cat
trait Bird
trait Catch
trait FullTummy

def catchIt(cat: Cat, bird: Bird): Cat with Catch;
def eatIt(consumer: Cat with Catch): Cat with FullTummy;

val story = (catchIt _) andThen (eatIt _)
story(new Cat, new Bird)
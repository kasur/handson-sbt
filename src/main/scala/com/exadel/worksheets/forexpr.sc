import java.nio.file.Files

val info = for {
  file <- new java.io.File("/home/kasur/tmp").listFiles
} yield (file.getName,Files.size(file.toPath))

info foreach println

def gcd(x: Long, y: Long):Long = {
  if(y == 0) x else gcd(y, x % y)
}

//test whether it is x = 2 ^ n => iff n & (n-1) == 0
(32 & 31) == 0
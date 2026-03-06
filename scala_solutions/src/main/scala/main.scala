import scala.util.boundary
import scala.util.boundary.break

@main
def main(): Unit = {
  println(checkOnesSegment("11111100001"))
}

def checkOnesSegment(s: String): Boolean = {
  // https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/description/?envType=daily-question&envId=2026-03-06
  var segment = false
  boundary:
    for i <- 1 until s.length() do
      if s.charAt(i) == '0' && !segment then segment = true
      if s.charAt(i) == '1' && segment then break(false)

    true
}


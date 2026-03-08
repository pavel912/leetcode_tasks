import scala.util.boundary
import scala.util.boundary.break

@main
def main(): Unit = {
  println(findDifferentBinaryString(Array("111","011","001")))
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

def findDifferentBinaryString(nums: Array[String]): String = {
  // https://leetcode.com/problems/find-unique-binary-string/?envType=daily-question&envId=2026-03-08

  val n = nums.length

  val ints = new Array[Int](n)

  for i <- 0 until n do
    ints(i) = Integer.parseUnsignedInt(nums(i), 2)

  boundary:
    for num <- 0 until 16 do
      if !ints.contains(num) then break(toBinaryString(num, n))

    toBinaryString(16, n)
}

def toBinaryString(num: Int, size: Int): String = {
  val res = Integer.toBinaryString(num)

  val zerosToAdd = size - res.length
  val zeros = "0" * zerosToAdd

  zeros + res
}


import scala.collection.mutable
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

def minMutation(startGene: String, endGene: String, bank: Array[String]): Int = {
  // https://leetcode.com/problems/minimum-genetic-mutation/?envType=study-plan-v2&envId=top-interview-150
  val queue = mutable.Queue[String](startGene)
  val visited = mutable.Set[String]()
  var cntIter = 0

  boundary:
    while queue.nonEmpty do
      val n = queue.size

      for i <- 0 until n do {
        val gene = queue.dequeue()

        if endGene.equals(gene) then break(cntIter)

        if !visited.contains(gene) then {
          visited.add(gene)

          for mut <- bank do {
            if isValidMutation(gene, mut) && !visited.contains(mut) then
              queue.append(mut)
          }
        }
      }

      cntIter += 1

    -1
}

def isValidMutation(oldGene: String, newGene: String) : Boolean = {
  var cntDiff = 0;

  for i <- 0 until 8 do
    if oldGene.charAt(i) != newGene.charAt(i) then cntDiff += 1

  cntDiff == 1
}


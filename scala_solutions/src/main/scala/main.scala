import scala.annotation.tailrec
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

def bitwiseComplement(n: Int): Int = {
  // https://leetcode.com/problems/complement-of-base-10-integer/?envType=daily-question&envId=2026-03-11
  // n ^ iterMaxVal(n)
  n ^ logMaxVal(n)
}

def logMaxVal(n: Int): Int = {
  if n == 0 then return 1

  val highestPower = Math.floor(Math.log(n) / Math.log(2)) + 1

  Math.pow(2, highestPower).toInt - 1
}

def iterMaxVal(n: Int): Int = {
  if n == 0 then return 1

  var highestPower = -1
  var num = n

  while num > 0 do {
    highestPower += 1
    num = num >> 1
  }

  var res = 0

  for i <- 0 to highestPower do
    res += (1 << i)

  res
}

def ladderLength(beginWord: String, endWord: String, wordList: List[String]): Int = {
  val n = beginWord.length
  val wordSet = mutable.Set[String](beginWord)

  for word <- wordList do wordSet.add(word)

  val adjList = buildAdjList(wordSet, n)

  val queue = mutable.Queue[String](beginWord)
  val visited = mutable.Set[String]()

  var seq = 1

  boundary:
    while queue.nonEmpty do
      val queueSize = queue.size

      for i <- 0 until queueSize do {
        val word = queue.dequeue()

        if !visited.contains(word) then {
          visited.add(word)

          for next <- adjList(word) do {
            if next.equals(endWord) then break(seq + 1)

            if !visited.contains(next) then
              queue.enqueue(next)
          }
        }
      }

      seq += 1

    0
}

def buildAdjList(wordSet: mutable.Set[String], n: Int): mutable.Map[String, mutable.ListBuffer[String]] = {
  val adjList = mutable.Map[String, mutable.ListBuffer[String]]()

  for word <- wordSet do {
    adjList.addOne((word, mutable.ListBuffer[String]()))

    for
      i <- 0 until n
      char <- 'a' to 'z'
    do {
      if word.charAt(i) != char then
        val newWord = word.substring(0, i) + char + word.substring(i + 1, n)
        if wordSet.contains(newWord) then
          adjList(word) += newWord
    }
  }

  adjList
}
class Trie() {
  // https://leetcode.com/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=top-interview-150

  class Node(var value: String, var isWord: Boolean, val children: mutable.Map[Char, Node])

  private val head = Node("", false, mutable.Map())

  def insert(word: String): Unit = {
    insert(word, 0, head)
  }

  @tailrec
  private def insert(word: String, index: Int, node: Node): Unit = {
    if index >= word.length then {
      node.isWord = true
      return
    }

    val char = word.charAt(index)
    if node.children.contains(char) then
      insert(word, index + 1, node.children(char))
    else {
      node.children.addOne((char, Node(word.substring(0, index + 1), false, mutable.Map())))
      insert(word, index + 1, node.children(char))
    }
  }

  def search(word: String): Boolean = {
    search(word, 0, head)
  }

  @tailrec
  private def search(word: String, index: Int, node: Node): Boolean = {
    if index >= word.length then return node.isWord

    val char = word.charAt(index)
    if !node.children.contains(char) then return false

    search(word, index + 1, node.children(char))
  }

  def startsWith(prefix: String): Boolean = {
    startsWith(prefix, 0, head)
  }

  @tailrec
  private def startsWith(prefix: String, index: Int, node: Node): Boolean = {
    if index >= prefix.length then return true

    val char = prefix.charAt(index)
    if !node.children.contains(char) then return false

    startsWith(prefix, index + 1, node.children(char))
  }

}

class WordDictionary() {

  // https://leetcode.com/problems/design-add-and-search-words-data-structure/?envType=study-plan-v2&envId=top-interview-150

  class Node(var value: String, var isWord: Boolean, val children: mutable.Map[Char, Node])

  private val head = Node("", false, mutable.Map())

  def addWord(word: String): Unit = {
    insert(word, 0, head)
  }

  @tailrec
  private def insert(word: String, index: Int, node: Node): Unit = {
    if index >= word.length then {
      node.isWord = true
      return
    }

    val char = word.charAt(index)
    if node.children.contains(char) then
      insert(word, index + 1, node.children(char))
    else {
      node.children.addOne((char, Node(word.substring(0, index + 1), false, mutable.Map())))
      insert(word, index + 1, node.children(char))
    }
  }

  def search(word: String): Boolean = {
    search(word, 0, head)
  }

  private def search(word: String, index: Int, node: Node): Boolean = {
    if index >= word.length then return node.isWord

    val char = word.charAt(index)
    if char == '.' then {
      return boundary:
        for (key, value) <- node.children do
          if search(word, index + 1, value) then break(true)

        false
    }

    if !node.children.contains(char) then return false

    search(word, index + 1, node.children(char))
  }

}


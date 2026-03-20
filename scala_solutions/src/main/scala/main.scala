import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
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

class Node(var value: String, var isWord: Boolean, val children: mutable.Map[Char, Node])

class Trie() {
  // https://leetcode.com/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=top-interview-150

  val head = Node("", false, mutable.Map())

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

  def delete(word: String): Unit = {
    val (crossroads, key) = delete(word, 0, head, head, word.charAt(0))
    if crossroads != null then crossroads.children -= key
  }

  private def delete(word: String, index: Int, node: Node, crossroads: Node, key: Char): (Node, Char) = {
    if index >= word.length then
      if node.children.nonEmpty then {
        node.isWord = false
        return (null, ' ')
      }

      return (crossroads, key)

    var cr = crossroads
    var k = key

    val char = word.charAt(index)
    if node.children.size > 1 then {
      cr = node
      k = char
    }

    if (node.children.contains(char)) then
      delete(word, index + 1, node.children(char), cr, k)

    (null, ' ')
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

def findWords(board: Array[Array[Char]], words: Array[String]): List[String] = {
  // https://leetcode.com/problems/word-search-ii/description/?envType=study-plan-v2&envId=top-interview-150
  val trie = Trie()
  val n = board.length
  val m = board(0).length

  words.foreach(trie.insert)

  val found = mutable.Set[String]()

  boundary:
    for
      i <- 0 until n
      j <- 0 until m
    do
      if found.size == words.length then break(found.toList)

      val foundWords = inspectCell(board, i, j, n, m, trie.head).toList.sortBy(x => -x.length)

      foundWords.foreach(trie.delete)
      found.addAll(foundWords)

    found.toList
}

def inspectCell(board: Array[Array[Char]], i: Int, j: Int, n: Int, m: Int, node: Node): mutable.Set[String] = {
  val visited = Array.ofDim[Boolean](n, m)
  val result = mutable.Set[String]()
  checkCell(board, i, j, n, m, node, visited, result)

  result
}

def checkCell(board: Array[Array[Char]], i: Int, j: Int, n: Int, m: Int, node: Node, visited: Array[Array[Boolean]], result: mutable.Set[String]): Unit = {
  if i < 0 || j < 0 || i >= n || j >= m || visited(i)(j) then return

  visited(i)(j) = true

  if node.children.contains(board(i)(j)) then
    val newNode = node.children(board(i)(j))

    if newNode.isWord then result.add(newNode.value)

    checkCell(board, i + 1, j, n, m, newNode, visited, result)
    checkCell(board, i, j + 1, n, m, newNode, visited, result)
    checkCell(board, i - 1, j, n, m, newNode, visited, result)
    checkCell(board, i, j - 1, n, m, newNode, visited, result)



  visited(i)(j) = false
}

def letterCombinations(digits: String): List[String] = {
  var ans = List[String]("")

  val digitMap = Map(
    '2' -> "abc",
    '3' -> "def",
    '4' -> "ghi",
    '5' -> "jkl",
    '6' -> "mno",
    '7' -> "pqrs",
    '8' -> "tuv",
    '9' -> "wxyz"
  )

  for d <- digits do
    ans = for word <- ans
              l <- digitMap(d)
    yield
        word + s"$l"

  ans
}

def combine(n: Int, k: Int): List[List[Int]] = {
  // https://leetcode.com/problems/combinations/?envType=study-plan-v2&envId=top-interview-150
  var ans = List[List[Int]](List[Int]())

  for k_ <- 0 until k do
    ans = for
      s <- ans
      n_ <- 1 to n
      if s.isEmpty || n_ > s.head
    yield
      n_ :: s


  ans
}

def permute(nums: Array[Int]): List[List[Int]] = {
  // https://leetcode.com/problems/permutations/?envType=study-plan-v2&envId=top-interview-150

  var ans = List[List[Int]](List[Int]())

  for k <- 0 until nums.length do
    ans = for
      s <- ans
      n <- nums
      if !s.contains(n)
    yield
      n :: s


  ans

}

def combinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {
  // https://leetcode.com/problems/combination-sum/?envType=study-plan-v2&envId=top-interview-150

  val ans = ListBuffer[List[Int]]()

  def helper(nums: List[Int], sum: Int): Unit = {
    if sum == target then {
      ans.append(nums)
      return
    } else if sum > target then
      return

    for num <- candidates do
      if nums.isEmpty || num >= nums.head then helper(num :: nums, sum + num)
  }

  helper(List[Int](), 0)

  ans.toList
}

def totalNQueens(n: Int): Int = {
  var ways = 0

  def newRow(taken: Array[String], queenPos: Int): Array[String] = {
    val next = Array.fill(n)("")

    for j <- 0 until n do
      for s <- taken(j) do
        s match {
          case 'l' => if j > 0 then next(j - 1) = next(j - 1).concat("l")
          case 'd' => next(j) = next(j).concat("d")
          case 'r' => if j < n - 1 then next(j + 1) = next(j + 1).concat("r")
      }

    if queenPos > 0 then next(queenPos - 1) = next(queenPos - 1).concat("l")
    next(queenPos) = next(queenPos).concat("d")
    if queenPos < n - 1 then next(queenPos + 1) = next(queenPos + 1).concat("r")

    next
  }

  def helper(taken: Array[String], row: Int): Unit = {
    if row > n then {
      ways += 1
      return
    }

    for j <- 0 until n do
      if taken(j).isEmpty then helper(newRow(taken, j), row + 1)
  }

  helper(Array.fill(n)(""), 1)

  ways
}

def generateParenthesis(n: Int): List[String] = {
  val ans = ListBuffer[String]()

  def helper(seq: String, open: Int, finished: Int) : Unit = {
    if finished == n then {
      ans.addOne(seq)
      return
    }

    if open + finished < n then helper(seq + "(", open + 1, finished)

    if open > 0 then helper(seq + ")", open - 1, finished + 1)
  }

  helper("", 0, 0)

  ans.toList
}

def exist(board: Array[Array[Char]], word: String): Boolean = {
  val n = board.length
  val m = board(0).length

  def dfs(i: Int, j: Int, k: Int): Boolean = {
    if (i >= n || i < 0 || j >= m || j < 0 || board(i)(j) == '*' || board(i)(j) != word(k)) return false

    if k == word.length - 1 then return true

    val char = board(i)(j)
    board(i)(j) = '*'

    val res = dfs(i + 1, j, k + 1) || dfs(i - 1, j, k + 1) || dfs(i, j + 1, k + 1) || dfs(i, j - 1, k + 1)

    board(i)(j) = char

    res
  }

  boundary:
    for
      i <- board.indices
      j <- board(i).indices
    do
      if dfs(i, j, 0) then break(true)

    false
}
import org.scalatest.funsuite.AnyFunSuite

class SolutionsTest extends AnyFunSuite {
  test("minMutations") {
    assert(minMutation("AACCGGTT", "AAACGGTA", Array("AACCGGTA","AACCGCTA","AAACGGTA")) == 2)
  }

  test("logValMax") {
    assert(logMaxVal(0) == 1)
    assert(logMaxVal(1) == 1)

    assert(logMaxVal(2) == 3)

    assert(logMaxVal(5) == 7)
    assert(logMaxVal(6) == 7)

    assert(logMaxVal(8) == 15)
    assert(logMaxVal(12) == 15)
    assert(logMaxVal(11) == 15)
  }

  test("iterValMax") {
    assert(iterMaxVal(0) == 1)
    assert(iterMaxVal(1) == 1)

    assert(iterMaxVal(2) == 3)

    assert(iterMaxVal(5) == 7)
    assert(iterMaxVal(6) == 7)

    assert(iterMaxVal(8) == 15)
    assert(iterMaxVal(12) == 15)
    assert(iterMaxVal(11) == 15)
  }

  test("bitwiseCompliment") {
    assert(bitwiseComplement(0) == 1)
    assert(bitwiseComplement(1) == 0)

    assert(bitwiseComplement(5) == 2)
    assert(bitwiseComplement(7) == 0)
    assert(bitwiseComplement(10) == 5)
    assert(bitwiseComplement(1073741823) == 0)
  }

  test("ledderLength") {
    assert(ladderLength("hit", "cog", List("hot","dot","dog","lot","log","cog")) == 5)

    assert(ladderLength("hit", "cog", List("hot","dot","dog","lot","log")) == 0)
  }

  test("trie") {
    val trie = Trie()

    trie.insert("apple")

    assert(trie.search("apple"))
    assert(!trie.search("app"))

    assert(trie.startsWith("app"))
    trie.insert("app")

    assert(trie.search("app"))

  }

  test("wordDictionary") {
    val dict = WordDictionary()

    dict.addWord("bad")
    dict.addWord("dad")
    dict.addWord("mad")

    assert(!dict.search("pad"))
    assert(dict.search("bad"))
    assert(dict.search(".ad"))
    assert(dict.search("b.."))
    assert(!dict.search(".."))
  }

  test("findWords") {
    val res = findWords(
      Array(
        Array('o','a','a','n'),
        Array('e','t','a','e'),
        Array('i','h','k','r'),
        Array('i','f','l','v')
      ),
      Array("oath","pea","eat","rain")
    )

    assert(res.contains("oath"))
    assert(res.contains("eat"))
  }

  test("letterCombinations") {
    assert(letterCombinations("23").length == 9)
  }

  test("combine") {
    assert(combine(4, 2).length == 6)
    assert(combine(6, 3).length == 20)
    assert(combine(1, 1).length == 1)
  }

  test("permute") {
    assert(permute(Array(1, 2, 3)).length == 6)

    assert(permute(Array(0, 1)).length == 2)

    assert(permute(Array(1)).length == 1)
  }

  test("combinationSum") {
    assert(combinationSum(Array(2, 3, 6, 7), 7).length == 2)

    assert(combinationSum(Array(2, 3, 5), 8).length == 3)

    assert(combinationSum(Array(2), 1).length == 0)
  }

  test("queens") {
    assert(totalNQueens(4) == 2)
    assert(totalNQueens(2) == 0)
    assert(totalNQueens(1) == 1)
  }

  test("generateParathesis") {
    assert(generateParenthesis(3).length == 5)
    assert(generateParenthesis(1).length == 1)
  }

  test("exist") {
    assert(exist(Array(Array('A','B','C','E'),Array('S','F','C','S'),Array('A','D','E','E')), "ABCCED"))
    assert(!exist(Array(Array('A','B','C','E'),Array('S','F','C','S'),Array('A','D','E','E')), "ABCB"))
  }

  test("sortedArrayToBST") {
    assert(sortedArrayToBST(Array(-10,-3,0,5,9)).value == 0)
  }
}

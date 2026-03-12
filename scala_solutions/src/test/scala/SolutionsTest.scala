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
}

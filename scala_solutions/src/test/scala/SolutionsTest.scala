import org.scalatest.funsuite.AnyFunSuite

class SolutionsTest extends AnyFunSuite {
  test("minMutations") {
    assert(minMutation("AACCGGTT", "AAACGGTA", Array("AACCGGTA","AACCGCTA","AAACGGTA")) == 2)
  }
}

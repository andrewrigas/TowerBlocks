package foundations.unit

import foundations.Exercises.B3.RecursionFun.RecursionFun.*
import foundations.Exercises.B4.AlgebraicDataTypes.AlgebraicDataTypes.Tree.*
import foundations.base.FoundationsSpecBase

class RecursionFunSpec extends FoundationsSpecBase {

  "RecursionFun" should {

    "gcd should return the Greatest Common Divisor in 2 values" in {
      greatestCommonDivisor(5, -60) shouldBe 5
    }

    "triangular should return the triangular of a value" in {
      triangular(3) shouldBe 6
    }

    "factorial should return the factorial of a value" in {}

    "nestedfunction should return the sum of the elements in the list" in {
      val list = List(1, 2, 3, 4)
      nestedFunction(list) shouldBe 10
    }

    "hashString should return a hashed string" in {
      hashString("Hello", x => x.isEmpty, x => x.concat("hashed")) shouldBe "Hellohashed"
      hashString("", x => x.isEmpty, x => x + "hashed") shouldBe ""
    }

    "aggOnRange should" ignore {
      val min                       = 1
      val max                       = 3
      val lambda: (Int, Int) => Int = (a, b) => a + b

      aggOnRange(min, max, lambda) shouldBe 6
      aggOnRange(max, min, lambda) shouldBe 6
    }

    "aggFactorialRange should return the sum of factorials in a range of 2 values" ignore {
      val x1 = 2
      val x2 = 4

      aggFactorialRange(x1, x2) shouldBe 32
    }

    // sumOfAllElements1

    // sumOfAllElements2

    // reverseList
    // addToTheBottom

    // Calculate Tree Sum
    "calculateTreeSum should calculate the sum of all leafs" in {
      val tree1Sum = 7
      val tree2Sum = 19

      val tree1 = Branch(Branch(Leaf(1), Leaf(2)), Leaf(4))
      val tree2 = Branch(
        Branch(Branch(Leaf(1), Leaf(2)), Leaf(4)),
        Branch(
          Branch(Branch(Leaf(3), Branch(Leaf(2), Leaf(1))), Leaf(2)),
          Leaf(4),
        ),
      )
      calculateSumOfTheTree(tree1) shouldBe tree1Sum
      calculateSumOfTheTree(tree2) shouldBe tree2Sum
    }

    // Remove ignore
    "mapOnTree should apply a function to leafs" ignore {

      val tree1 = Branch(Branch(Leaf(1), Leaf(2)), Leaf(4))
      val tree2 = Branch(
        Branch(Branch(Leaf(1), Leaf(2)), Leaf(4)),
        Branch(
          Branch(Branch(Leaf(3), Branch(Leaf(2), Leaf(1))), Leaf(2)),
          Leaf(4),
        ),
      )

      val tree1Res = Branch(Branch(Leaf(2), Leaf(3)), Leaf(5))
      val tree2Res = Branch(
        Branch(Branch(Leaf(2), Leaf(3)), Leaf(5)),
        Branch(
          Branch(Branch(Leaf(4), Branch(Leaf(3), Leaf(2))), Leaf(3)),
          Leaf(5),
        ),
      )
      mapOnTree(tree1, (x: Int) => x + 1) shouldBe tree1Res
      mapOnTree(tree2, (x: Int) => x + 1) shouldBe tree2Res
    }

    "CountChange should count the different ways you can make change for an amount" in {}

  }
}

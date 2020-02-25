package foundations.unit

import foundations.FunctionalWay._
import foundations.base.FoundationsSpecBase

class FunctionalWaySpec extends FoundationsSpecBase {

  "Functional exercises" should {

    "f11 and f12 should return + 1 on value" in {

      val res11 = f11(2)
      val res12: Int = f12(4)

      res11 shouldBe 3
      res12 shouldBe 5
    }

    "f21 and f22 should return the sum of 2 values" in {

      val res21 = f21(1, 2)
      val res22 = f22(5, 3)

      res21 shouldBe 3
      res22 shouldBe 8
    }

    "f31 and f32 should return the sum of of 2 values" in {
      val res31 = f31(3)(2)
      val res32 = f32(4)(4)

      res31 shouldBe 5
      res32 shouldBe 8
    }

    "funF should return the result of a value applied by function" in {
      val res = funF(2, x => Math.pow(x, 2).toInt)

      res shouldBe 4
    }

    "addition should return" in {}

    //substraction
    //multiplication
    //division

    "optionExample should return 0 on None and Value on Some" in {
      lazy val value = 5
      lazy val noneCase = 0
      lazy val someValue = Some(5)
      lazy val noneValue = None

      optionExample(someValue) shouldBe value
      optionExample(noneValue) shouldBe noneCase
    }

    "optionExample2 should" in {}

    "ListExamples " which {
      lazy val head = 5
      lazy val nilCase = 0
      lazy val list1 = Nil
      lazy val list2 = List(head)
      lazy val list3 = List(head, 6, 4)

      "listExample should return Head or 0" in {

        listExample(list1) shouldBe nilCase
        listExample(list2) shouldBe head
        listExample(list3) shouldBe head
      }

      "listExample2 should return Head or 0" in {

        listExample2(list1) shouldBe nilCase
        listExample2(list2) shouldBe head
        listExample2(list3) shouldBe head
      }

      //Remove ignore
      "listSum2RandomElements should return the sum of the first 2 elements" ignore {
        val list1 = List(3, 4)
        val list2 = List(1)
        val list3 = Nil

        listSum2RandomElements(list1, (x, y) => x + y) shouldBe Some(7)
        listSum2RandomElements(list2, (x, y) => x + y) shouldBe None
        listSum2RandomElements(list3, (x, y) => x + y) shouldBe None
      }
    }

  }

}

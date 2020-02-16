package foundations.unit

import foundations.FunctionalWay._

object FunctionalWaySpec extends FoundationsSpecBase {

  "Functional exercises" should {

    "f11 and f12 should return + 1 on value" in {
      val res11 = f11(2)
      val res22 = f12(4)

      res11 shouldBe 3
      res22 shouldBe 4

    }

  }

}

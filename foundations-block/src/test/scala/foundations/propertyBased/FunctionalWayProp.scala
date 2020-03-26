package foundations.propertyBased

import foundations.base.FoundationsPropBase
import foundations.Exercises.B2.FunctionalWay.FunctionalWay._
import foundations.Solutions.B2.FunctionalWay.FunctionalWay._
import org.scalacheck.Prop.{forAll, propBoolean}
import org.scalacheck.Properties

class FunctionalWayProp
  extends Properties("Functional")
    with FoundationsPropBase {

  //Currying
  property("f11 should return Value+1") = forAll { value: Int =>
    f11(value) == value + 1
    //f11(value) > value
  }

  property("listExample should always return head or zero") = forAll(genStringWeird, genStringWeird, genIntDates , genIntDates) {
    (str1: String, str2: String, x1: Int, x2: Int) =>
      val strConcat: String = str1 + str2 + (x1 + x2)
      val f211Res: String = f211(str1, str2)(x1)(x2)
      strConcat.contains(f211Res)
  }

  property("listExample should always return head or zero") = forAll {
    list: List[Int] =>
      list.nonEmpty ==> (listExample(list) == list.head)
    //list.isEmpty ==> (listExample(list) == 0)
  }

  //  property("calculator should passed within this Integer Range") =
  //    forAll(integerRangeGen) { (x1: Int, x2: Int) =>
  //      ???
  //    }

}

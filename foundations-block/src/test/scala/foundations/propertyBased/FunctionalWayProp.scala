package foundations.propertyBased

import foundations.base.FoundationsPropBase
import foundations.Exercises.B2.FunctionalWay.FunctionalWay._
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

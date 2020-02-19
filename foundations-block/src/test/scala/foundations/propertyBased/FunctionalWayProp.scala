package foundations.propertyBased
import foundations.FunctionalWay._
import foundations.base.FoundationsPropBase
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import org.scalacheck.Prop.propBoolean

class FunctionalWayProp
    extends Properties("Functional")
    with FoundationsPropBase {

  //Currying
  property("f11 should return Value+1") = forAll { value: Int =>
    f11(value) == value + 1
    //f11(value) > value - 1
  }

  property("listExample should always return head or zero") = forAll {
    list: List[Int] =>
      list.nonEmpty ==> (listExample(list) == list.head)
    //list.isEmpty ==> (listExample(list) == 0)
  }

//  property("calculator should passed with the Integer Range") =
//    forAll(integerRangeGen) { (x1: Int, x2: Int) =>
//      ???
//    }

}

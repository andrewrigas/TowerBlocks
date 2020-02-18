package foundations.base

import org.scalacheck.Gen

trait FoundationsPropBase {

  lazy val integerRangeGen = Gen.choose(0, 31)

  lazy val genOnOfThisStrings = Gen.oneOf("Hello", "World", "!!!")

}

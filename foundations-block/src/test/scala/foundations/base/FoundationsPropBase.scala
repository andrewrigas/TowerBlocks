package foundations.base

import org.scalacheck.Gen

trait FoundationsPropBase {

  lazy val genIntDates = Gen.choose(0, 28)

  lazy val genStringWeird = Gen.oneOf("Hello", "World", "!!!", "1@@!!!@@@DD", ".;s'l[[[]]")

}

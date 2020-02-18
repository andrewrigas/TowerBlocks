package foundations

import org.scalacheck.Gen

package object propertyBased {

  lazy val integerRangeGen = Gen.choose(0, 31)

}

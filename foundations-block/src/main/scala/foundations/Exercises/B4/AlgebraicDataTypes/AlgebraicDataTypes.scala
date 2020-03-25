package foundations.Exercises.B4.AlgebraicDataTypes

object AlgebraicDataTypes extends App {

  //Sums And Products

  //Sum Examples

  final case class Human(name: String)

  final case class Animal(name: String)

  //Product Examples

  sealed abstract class Spirits

  case object Vodka extends Spirits

  case object Whiskey extends Spirits

  //Hybrid Example

  sealed trait Car {
    def model: String
  }

  final case class BMW(model: String) extends Car

  final case class Jaguar(model: String) extends Car

  //Type Class or Higher Kinded Type or Parametric Type
  sealed trait OptionB[+A] {
    def apply[A1 >: A](value: A1): OptionB[A1] =
      if (value == null) OptionB.NoneB else OptionB.SomeB(value)
  }

  object OptionB {
    final case class SomeB[+A](value: A) extends OptionB[A]

    case object NoneB extends OptionB[Nothing]
  }

  //List Example
  sealed abstract class ListExample[+A]

  object ListExample {

    final case class OneOrMore[+A](head: A, tail: ListExample[A])
        extends ListExample[A] {}

    case object Last extends ListExample[Nothing] {}
  }

  //Tree Example
  sealed trait Tree[+A]

  object Tree {
    final case class Branch[+A](left: Tree[A], right: Tree[A]) extends Tree[A]

    final case class Leaf[+A](value: A) extends Tree[A]

  }

  //Either Exercise

  //Try Exercise

  //Write Type Alias for Option using Either

  //Write Type Alias for Try using Either
}

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


  }

  object OptionB {

    def apply[A1](value: A1): OptionB[A1] =
      if (value == null) NoneB else SomeB(value)

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
  //Either[+A,+B] takes 2 types
  //Either is hybrid type
  //Either can be Left[A] or Right[B] where both take a single type
  //Either flatMap and map function handle the Right[B] case, so right is the nice path

  //Try Exercise
  //Try[+A] take 1 type
  //Try is hybrid
  //Try can be Success[A] or Failure[Exception] where one takes a generic type
  //And Failure takes an Exception


  //Try write Type Aliases for Option[A] using Either

  //Try write Type Aliases for Try[A] using Either

}

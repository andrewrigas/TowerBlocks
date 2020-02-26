package foundations

object AlgebraicDataTypes extends App {

  //Sums And Products

  //Sum Examples

  final case class Human(name: String)

  final case class Animal(name: String)

  //Product Examples

  sealed trait Spirits {
    def brand: String
  }

  case object Vodka extends Spirits {
    val brand = "Absolut"
  }

  case object Whiskey extends Spirits {
    val brand = "Johny Walker"
  }

  //Hybrid Example

  sealed abstract class Car

  final case class BMW(model: String) extends Car

  final case class Jaguar(model: String) extends Car

  //Type Class
  sealed trait Option[+A] {
    def apply[A1 >: A](value: A1): Option[A1] =
      if (value == null) Option.None else Option.Some(value)
  }

  object Option {
    final case class Some[+A](value: A) extends Option[A]

    case object None extends Option[Nothing]
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
  //Try to write an Either Data Type

}

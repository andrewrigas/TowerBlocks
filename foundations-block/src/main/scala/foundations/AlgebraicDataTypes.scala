package foundations

object AlgebraicDataTypes extends App {

  sealed trait Option[+A] {
    def apply[A1 >: A](value: A1): Option[A1] =
      if (value == null) Option.None else Option.Some(value)
  }

  object Option {
    final case class Some[A](value: A) extends Option[A]

    case object None extends Option[Nothing]
  }

  //List Example
  sealed abstract class ListExample[+A]

  object ListExample {

    final case class OneOrMore[A](head: A, tail: ListExample[A])
        extends ListExample[A] {}

    case object Last extends ListExample[Nothing] {}
  }

  //Tree Example
  sealed trait Tree[+A]

  object Tree {
    final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

    final case class Leaf[A](value: A) extends Tree[A]

  }

  //Either Example




  def map[T, A](list: List[T], f: T => A): List[A] = ???

  def flatMap[T, A](list: List[T], f: T => List[A]): List[A] = ???

  def flatMap[F[_], T, A](fa: F[T], f: T => F[A]): F[A] = ???

}

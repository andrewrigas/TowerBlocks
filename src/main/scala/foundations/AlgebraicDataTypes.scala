package foundations

object AlgebraicDataTypes extends App {

  sealed trait Option[+A] {
    def apply[A1 >: A](value: A1): Option[A1] = if(value == null) None else Some(value)
  }

  final case class Some[A](value: A) extends Option[A]

  case object None extends Option[Nothing]

  sealed abstract class ListExample[+A]

  final case class OneOrMore[A](head: A, tail: ListExample[A])
      extends ListExample[A] {}

  case object Last extends ListExample[Nothing] {}

  sealed abstract class Tree[+A] {}

  final case class Leaf[A](left: Tree[A], right: Tree[A]) extends Tree[A] {}

  case object Root extends Tree[Nothing] {}

  def map[T, A](list: List[T], f: T => A): List[A] = ???

  def flatMap[T, A](list: List[T], f: T => List[A]): List[A] = ???

  def flatMap[F[_], T, A](fa: F[T], f: T => F[A]): F[A] = ???

}

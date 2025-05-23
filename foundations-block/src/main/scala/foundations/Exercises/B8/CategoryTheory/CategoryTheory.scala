package foundations.Exercises.B8.CategoryTheory

object CategoryTheory extends App {

  trait Monoid[A] {
    def value: A
    def identity: A
    def compose(x: A): A
  }

  given intSemiGroupSum: Monoid[Int] = new Monoid[Int] {
    val value: Int = 10

    override def compose(x: Int): Int = this.value + x

    override def identity: Int = this.value + 0
  }

  given intSemiGroupProduct: Monoid[Int] = new Monoid[Int] {
    val value: Int = 10

    override def compose(x: Int): Int = this.value * x

    override def identity: Int = this.value * 1
  }

  given stringSemiGroupConcat: Monoid[String] = new Monoid[String] {
    val value: String = "Hello"

    override def compose(x: String): String = this.value + x

    override def identity: String = this.value + ""
  }

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  trait Applicative[F[_]] extends Functor[F] {
    def pure[A](a: A): F[A]

    def ap[A, B](fa: F[A])(ff: F[A => B]): F[B]

    override def map[A, B](fa: F[A])(f: A => B): F[B] = ap[A, B](fa)(pure(f))
  }

  trait Monad[F[_]] extends Applicative[F] {
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

    def flatten[A](ffa: F[F[A]]): F[A] = flatMap(ffa)(identity)

    override def ap[A, B](fa: F[A])(ff: F[A => B]): F[B] =
      flatMap(ff)(a => map(fa)(a))

    override def map[A, B](fa: F[A])(f: A => B): F[B] =
      flatMap(fa)(a => pure(f(a)))
  }

  sealed abstract class Maybe[+A]

  final case class Just[A](a: A) extends Maybe[A] {
    def apply(a: A): Maybe[A] = if (a == null) Empty else Just(a)
  }

  case object Empty extends Maybe[Nothing]

  sealed abstract class ZeroOrMore[+A] {
    def append[B >: A](other: ZeroOrMore[B]): ZeroOrMore[B]
  }

  final case class OneOrMore[A](head: A, tail: ZeroOrMore[A]) extends ZeroOrMore[A] {
    override def append[B >: A](other: ZeroOrMore[B]): ZeroOrMore[B] =
      OneOrMore(head, tail.append(other))
  }

  case object Zero extends ZeroOrMore[Nothing] {
    override def append[B >: Nothing](other: ZeroOrMore[B]): ZeroOrMore[B] =
      other
  }

  given maybeFunctor: Functor[Maybe] = new Functor[Maybe] {
    override def map[A, B](fa: Maybe[A])(f: A => B): Maybe[B] = fa match {
      case Just(a) => Just(f(a))
      case Empty   => Empty
    }
  }

  given zeroOrMoreFunctor: Functor[ZeroOrMore] = new Functor[ZeroOrMore] {
    override def map[A, B](fa: ZeroOrMore[A])(f: A => B): ZeroOrMore[B] =
      fa match {
        case OneOrMore(head, tail) => OneOrMore(f(head), map(tail)(f))
        case Zero                  => Zero
      }
  }

  given maybeApplicative: Applicative[Maybe] = new Applicative[Maybe] {
    override def pure[A](a: A): Maybe[A] = Just(a)

    override def ap[A, B](fa: Maybe[A])(ff: Maybe[A => B]): Maybe[B] =
      (fa, ff) match {
        case (Just(a), Just(f)) => pure(f(a))
        case _                  => Empty
      }
  }

  given zeroOrMoreApplicative: Applicative[ZeroOrMore] = new Applicative[ZeroOrMore] {
    override def pure[A](a: A): ZeroOrMore[A] = OneOrMore(a, Zero)

    override def ap[A, B](
        fa: ZeroOrMore[A]
    )(ff: ZeroOrMore[A => B]): ZeroOrMore[B] = (fa, ff) match {
      case (Zero, Zero)                    => Zero
      case (Zero, OneOrMore(fHead, fTail)) => Zero
      case (OneOrMore(aHead, _), OneOrMore(fHead, Zero)) =>
        OneOrMore(fHead(aHead), Zero)
      case (OneOrMore(aHead, aTail), OneOrMore(fHead, fTail)) => ???
      case _                                                  => Zero
    }
  }

  given maybeMonad: Monad[Maybe] = new Monad[Maybe] {
    override def flatMap[A, B](fa: Maybe[A])(f: A => Maybe[B]): Maybe[B] =
      fa match {
        case Just(a) => f(a)
        case Empty   => Empty
      }

    override def pure[A](a: A): Maybe[A] = Just(a)
  }

  given zeroOrMore: Monad[ZeroOrMore] = new Monad[ZeroOrMore] {
    override def flatMap[A, B](
        fa: ZeroOrMore[A]
    )(f: A => ZeroOrMore[B]): ZeroOrMore[B] = fa match {
      case Zero                  => Zero
      case OneOrMore(head, tail) => f(head).append(flatMap(tail)(f))
    }

    override def pure[A](a: A): ZeroOrMore[A] = OneOrMore(a, Zero)
  }
}

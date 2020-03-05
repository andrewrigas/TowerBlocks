package foundations

object Experiments extends App {

  sealed trait Option[A] // An invariant class

  //Same level of abstraction
  object Option {

    final case class Some[A](value: A) extends Option[A]

    //case object None[A] extends Option[A]
  }

  //val maybeValue: Option[Int] = Option.None

  sealed trait Option1[+A] // A covariant class

  //I can accept subclasses
  object Option1 {

    final case class Some1[+A](value: A) extends Option1[A]

    case object None extends Option1[Nothing]
  }

  val maybeValue1: Option1[Int] = Option1.None

  sealed trait Option2[-A] // An invariant class

  //I can accept superclasses
  object Option2 {

    final case class Some2[A](value: A) extends Option2[A]

    case object None extends Option2[AnyVal]
  }

  val maybeValue: Option2[Int] = Option2.None
}

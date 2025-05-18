package foundations.Experiments

object VariancesExp extends App {

  sealed trait Option[A] // An invariant class

  // Same level of abstraction
  object Option {

    final case class Some[A](value: A) extends Option[A]

    // Cant create an object with a Generic Type
    // case object None[A] extends Option[A]
  }

  // val maybeValue: Option[Int] = Option.None

  sealed trait Option1[+A] // A covariant class

  // I can accept subclasses
  object Option1 {

    final case class Some1[+A](value: A) extends Option1[A]

    case object None extends Option1[Nothing]
  }

  // +A covariant make my statement legal insert a Nothing type into an Int "Nothing is a subclass of Int"
  val maybeValue1: Option1[Int] = Option1.None

  sealed trait Option2[-A] // An invariant class

  // I can accept superclasses
  object Option2 {

    final case class Some2[A](value: A) extends Option2[A]

    case object None extends Option2[AnyVal]
  }

  // -A invariant make my statement legal insert an AnyVal type into an Int "AnyVal is a supertype of Int"
  val maybeValue: Option2[Int] = Option2.None
}

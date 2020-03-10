package foundations.Solutions.B4.AlgebraicDataTypes

object AlgebraicDataTypes extends App {

  //Either Exercise
  sealed trait EitherB[+A, +B] extends Product with Serializable //Scala shows Least Parents to force the compiler show Either instead of Product and Serializable which are magically extends

  object EitherB {
    final case class RightB[+B](value: B) extends EitherB[Nothing, B]
    final case class LeftB[+A](value: A) extends EitherB[A, Nothing]
  }

  //Try Exercise
  sealed trait TryB[+A]

  object TryB {
    final case class SuccessB[+A](value: A) extends TryB[A]
    final case class FailureB(value: Throwable) extends TryB[Throwable]
  }
}

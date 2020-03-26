package foundations.Solutions.B4.AlgebraicDataTypes

object AlgebraicDataTypes extends App {

  //Either Exercise
  //Either[+A,+B] takes 2 types
  //Either is hybrid type
  //Either can be Left[A] or Right[B] where both take a single type
  //Either flatMap and map function handle the Right[B] case, so right is the nice path
  sealed trait EitherB[+A, +B] extends Product with Serializable //Scala shows Least Parents to force the compiler show Either instead of Product and Serializable which are magically extends

  object EitherB {
    final case class RightB[+B](value: B) extends EitherB[Nothing, B]
    final case class LeftB[+A](value: A) extends EitherB[A, Nothing]
  }


  //Try Exercise
  //Try[+A] take 1 type
  //Try is hybrid
  //Try can be Success[A] or Failure[Exception] where one takes a generic type
  //And Failure takes an Exception
  sealed trait TryB[+A]

  object TryB {
    final case class SuccessB[+A](value: A) extends TryB[A]
    final case class FailureB(value: Throwable) extends TryB[Throwable]
  }

  //Try write Type Aliases for Option[A] using Either
  type OptionC[+A] = EitherB[Nothing,A]

  //Try write Type Aliases for Try[A] using Either
  type TryC[+A] = EitherB[Exception,A]
}

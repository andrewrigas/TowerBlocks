package foundations.Solutions.B4.AlgebraicDataTypes

import foundations.Exercises.B4.AlgebraicDataTypes.AlgebraicDataTypes._
import foundations.Exercises.B4.AlgebraicDataTypes.AlgebraicDataTypes.OptionB._
import foundations.Solutions.B4.AlgebraicDataTypes.AlgebraicDataTypes.TryB._

object AlgebraicDataTypes extends App {

  //Either Exercise
  sealed trait EitherB[+A, +B] extends Product with Serializable //Scala shows Least Parents to force the compiler show Either instead of Product and Serializable which are magically extends

  object EitherB {
    final case class RightB[+B](value: B) extends EitherB[Nothing, B]
    final case class LeftB[+A](value: A) extends EitherB[A, Nothing]
  }

  //Try Exercise
  sealed trait TryB[+A] { self =>
    def toOption: OptionB[A] = {
      self match {
        case SuccessB(value) => SomeB(value)
        case FailureB(_)     => NoneB
      }
    }
    okPro
  }

  object TryB {
    final case class SuccessB[+A](value: A) extends TryB[A]
    final case class FailureB(value: Throwable) extends TryB[Throwable]
  }

  //Write Type Alias for Option using Either
  type OptionB[+A] = EitherB[Unit, A]

  //Write Type Alias for Try using Either
  type TryC[+A] = EitherB[Throwable, A]
}

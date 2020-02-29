package foundations

object Experiments extends App {

  sealed trait Option[A]

  object Option {

    final case class Some[A](value: A) extends Option[A]

    //case object None[A] extends Option[A]
  }

  import Option._

  //val maybeValue: Option[Int] = None
}

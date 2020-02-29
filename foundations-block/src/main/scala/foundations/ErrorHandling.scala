package foundations

import scala.util.Try

object ErrorHandling {

  def division(dividend: Double, divisor: Double): Option[Double] = {
    Try(dividend / divisor).toOption match {
      case Some(value) => Some(value)
      case None        => None
    }
  }

}

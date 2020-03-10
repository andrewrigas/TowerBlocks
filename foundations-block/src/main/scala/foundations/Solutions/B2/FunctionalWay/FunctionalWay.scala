package foundations.Solutions.B2.FunctionalWay

object FunctionalWay extends App {

  //Exercises
  //Create raw functions that takes 2 parameters and perform an operation
  val sum: (Int, Int) => Int = (x, y) => x + y
  val subtrahend: (Int, Int) => Int = (x, y) => x + y
  val product: (Int, Int) => Int = (x, y) => x * y
  val quotient: (Int, Int) => Int = (x, y) => x / y

  //Create a function that has input of 2 Parameters and pass the above functions Type to perform the operation
  def calculator(x: Int, y: Int)(f: (Int, Int) => Int): Int = f(x, y)

  //Exercise
  //Apply a Lambda/Anonymous Function on an Int and return Option
  def flatMapOption(maybeInt: Option[Int],
                    fun: Int => Option[Int]): Option[Int] = maybeInt match {
    case Some(value) => fun(value)
    case None        => None
  }

  //Exercise
  //Higher Order Function
  //Apply a Lambda/Anonymous Function on the first 2 elements in a list
  def listSum2RandomElements(list: List[Int],
                             f: (Int, Int) => Int): Option[Int] = list match {
    case _                      => None
    case head :: second :: tail => Some(f(head, second))
  }
}

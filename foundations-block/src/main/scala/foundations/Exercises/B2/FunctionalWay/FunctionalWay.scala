package foundations.Exercises.B2.FunctionalWay

object FunctionalWay extends App {

  lazy val f11: Int => Int = (x: Int) => x + 1

//  println(s"f11 = ${f11(1)}")

  def f12(x: Int): Int = x + 1

  //println(s"f12 = ${f12(2)}")

  lazy val f21: (Int, Int) => Int = (x1: Int, x2: Int) => x1 + x2

  //println(s"f21 = ${f21(1, 2)}")

  def f22(x1: Int, x2: Int): Int = x1 + x2

  //println(s"f22 = ${f22(1, 2)}")

  lazy val f31: Int => Int => Int = (x1: Int) => (x2: Int) => x1 + x2

  //println(s"f31 = ${f31(1)(2)}")

  def f32(x1: Int)(x2: Int): Int = x1 + x2

  //println(s"f32 = ${f32(2)(3)}")

  //Currying
  lazy val f311 = f31(1)

  //val f321 = f32(3)
  //val f321: Int => Int = f32(3)

  //println(s"f311 = ${f311(1)}")

  //println(s"f321 = ${f321(1)}")

  def funF(x: Int, f: Int => Int): Int = f(x)

  //Exercises
  //Create raw functions
//  val sum = ???
//  val subtrahend = ???
//  val product = ???
//  val quotient = ???

  //Create functions that has an input a function
  def addition = ???
  def substraction = ???
  def multiplication = ???
  def division = ???

  //println(s"Addition: ")
  //println(s"Subtraction:")
  //println()
  //println()

  //Pattern Matching
  def optionExample(maybeInt: Option[Int]): Int = maybeInt match {
    case Some(value) => value
    case None        => 0
  }

  //Exercise
  //Apply a Lambda/Anonymous Function on an Int and return Option
  def flatMapOption(maybeInt: Option[Int],
                    fun: Int => Option[Int]): Option[Int] = ???

  def listExample(list: List[Int]) = list match {
    case Nil          => 0
    case head :: tail => head
  }

  def listExample2(list: List[Int]): Int = list match {
    case Nil                    => 0
    case head :: second :: tail => head
    case head :: tail           => head
  }

  //Exercise
  //Apply on the first 2 elements in a list a Lambda/Anonymous Function
  def listSum2RandomElements(list: List[Int],
                             f: (Int, Int) => Int): Option[Int] = ???
}

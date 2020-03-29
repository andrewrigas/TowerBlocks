package foundations.Exercises.B2.FunctionalWay

object FunctionalWay extends App {

  //try remove lazy and run the test
  lazy val f11: Int => Int = (x: Int) => x + 1

//  println(s"f11 = ${f11(1)}")

  def f12(x: Int): Int = x + 1

  //println(s"f12 = ${f12(2)}")

  lazy val f21: (Int, Int) => Int = (x1: Int, x2: Int) => x1 + x2

  //println(s"f21 = ${f21(1, 2)}")

  //Exercise
  //Write a function that takes a Tuple of Strings and 2 Integers
  //Sum the 2 Integers Together
  //Concat the Tuple Strings with Sum return a String
  //For Example
  //f211(("Hello","World"), 1 , 2) = "HelloWorld3"


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

  //Exercise
  //Write a function that takes a Tuple of Strings and 2 Integers
  //Sum the 2 Integers Together and print them with the Strings
  //For Example
  //f211(("Hello","World"), 1 , 2) = "HelloWorld3"
  //Create a type alias for that exercise

  type TypeAlias = Nothing
  val f211: TypeAlias = ???

  //println(s"f311 = ${f311(1)}")

  //println(s"f321 = ${f321(1)}")

  //Higher Order Function
  def funF(x: Int, f: Int => Int): Int = f(x)

//  println(funF(5 , x => x + 1))
//  println(funF(5 , f11))

  //Exercises
  //Create raw functions that takes 2 parameters and perform an operation
  val sum = ???
  val subtrahend = ???
  val product = ???
  val quotient = ???

  //Create a higher order function that has input of 2 Parameters an then pass one of the above operations
  def calculator = ???

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

  //Pattern Matching with order
  def listExample2(list: List[Int]): Int = list match {
    case Nil                    => 0
    case head :: second :: tail => head
    case head :: tail           => head
  }

  //Exercise
  //Higher Order Function
  //Apply a lambda function on the first 2 elements in a list
  def listSum2RandomElements(list: List[Int],
                             f: (Int, Int) => Int): Option[Int] = ???


  //Compose 2 or more functions together
  def andThen(value: Int, f: Int => Int): Int = {
    f(value)
  }

  //Exercise
  //Higher order Function
  //
}

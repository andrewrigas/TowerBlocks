package foundations

object Main extends App {

  val x = 14
  val i: String = "Info"
  var id = "Info"
  var d: Int = 3
  //var g

  trait Trait
  //Support multiple inheritance
  //Allowed to add a trait to an object
  //Does not contain constructor

  abstract class AbstractClass()
  //Does not support multiple inheritance
  //Not allowed to add an abstract class to an object
  //Contain constructor

  class Class()

  object Object
  //Singleton Pattern
  //Only one instance

  val listExample = List(1, 2, 34, 5, 6)

  val setExample = Set(1, 2, 3, 4, 3, 5)

  val mapExample = Map(1 -> "1", 2 -> "2", 3 -> "3", 1 -> "11")

  println(listExample)

  println(setExample)

  println(mapExample)

  val forExp = for (element <- listExample) yield element + 1

  val mapExp = listExample.map(x => x + 1)

  val flatMapExp = listExample.flatMap(x => listExample.map(_ + x))

  println(s"y = $y")

  println(s"z = $z")

  println(s"whatAmI = $whatAmI")

  val y = "Am I Lazy ?"

  lazy val z = "I am lazy like you"

  def whatAmI = "I am lazy like anyone else"

  val f11: Int => Int = (x: Int) => x + 1

  println(s"f11 = ${f11(1)}")

  def f12(x: Int): Int = x + 1

  println(s"f12 = ${f12(2)}")

  val f21: (Int, Int) => Int = (x1: Int, x2: Int) => x1 + x2

  println(s"f21 = ${f21(1, 2)}")

  def f22(x1: Int, x2: Int): Int = x1 + x2

  println(s"f22 = ${f22(1, 2)}")

  val f31: Int => Int => Int = (x1: Int) => (x2: Int) => x1 + x2

  println(s"f31 = ${f31(1)(2)}")

  def f32(x1: Int)(x2: Int): Int = x1 + x2

  println(s"f32 = ${f32(2)(3)}")

  //Currying

  val f311: Int => Int = f31(1)

  val f321: Int => Int = f32(3)
  //val f321: Int => Int = f32(3)

  println(s"f311 = ${f311(1)}")

  println(s"f321 = ${f321(1)}")

  def funF(x: Int, f: Int => Int): Int = f(x)

  def map[T, A](list: List[T], f: T => A): List[A] = ???

  def flatMap[T, A](list: List[T], f: T => List[A]): List[A] = ???

  sealed abstract class ListExample[+A] {}

  final case class OneOrMore[A](head: A, tail: ListExample[A])
      extends ListExample[A] {}

  case object Last extends ListExample[Nothing] {}

  sealed abstract class Tree[+A] {}

  final case class Leaf[A](left: Tree[A], right: Tree[A]) extends Tree[A] {}

  case object Root extends Tree[Nothing] {}

  def flatMap[F[_], T, A](fa: F[T], f: T => F[A]): F[A] = ???

}

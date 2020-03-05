package foundations.Exercises.B1.Foundations

object Foundations extends App {

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

  //Collections
  val listExample: List[Int] = List(1, 2, 3)

  val listExample2: List[Int] = 1 :: 2 :: 3 :: Nil

  val setExample: Set[Int] = Set(1, 2, 3, 4, 3, 5)

  val mapExample: Map[Int, String] =
    Map(1 -> "1", 2 -> "2", (3, "3"), (1, "11"))

  val rangeExample: Range = 1 to 10

  //println(listExample)

  //println(setExample)

  //println(mapExample)

  for (i <- 1 to 10) {
    i
  }

  val forExp = for (element <- listExample) yield element + 1

  val mapExp = listExample.map(x => x + 1)

  val flatMapExp = listExample.flatMap(x => listExample.map(_ + x))

  val flattenTheList = List(List(1, 2, 3), List(4, 5), List(6, 7))

  val flattenList: List[Int] = flattenTheList.flatten

  //  println(s"y = $y")

//  println(s"z = $z")

//  println(s"whatAmI = $whatAmI")

  val y = "Am I Lazy ?"

  lazy val z = "I am lazy like you"

  def whatAmI = "I am lazy like anyone else"

}

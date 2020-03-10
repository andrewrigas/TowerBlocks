package foundations.Solutions.B3.RecursionFun

object RecursionFun extends App {

  //Exercises
  //Calculate Factorial recursively
  //For example:
  // n = 4
  // factorial == 4! => 4 * 3 * 2 * 1
  def factorial(n: Int): Int = if (n <= 0) 1 else n * factorial(n - 1)

  def parenthesisBalance(chars: List[Char]): Boolean = {

    def parCount(chars: List[Char], acc: Int = 0): Int = {
      if (acc < 0) acc
      else {
        chars match {
          case Nil       => 0
          case '}' :: xs => parCount(xs, acc - 1)
          case '{' :: xs => parCount(xs, acc + 1)
          case _ :: xs   => parCount(xs, acc)
        }
      }
    }
    parCount(chars) == 0
  }

//  println(parenthesisBalance(List('{', '}', '}')))
//  println(parenthesisBalance(List('}', '{', '}', '}')))
//  println(parenthesisBalance(List('{', '{', '}', '}')))

  //Exercise
  //Apply factorial on a range of values and aggregate them
  //For example:
  //a = 1, b = 5
  //Aggregation f1 + f2
  //aggFactorialRange == 1! + 2! + 3! + 4! + 5!
  def aggFactorialRange(a: Int, b: Int): Int =
    if (a == b) factorial(a)
    else if (a < b) factorial(a) + aggFactorialRange(a + 1, b)
    else factorial(b) + aggFactorialRange(a, b + 1)

  //Exercise
  //Apply a function on a range of two values
  //For example:
  // a = 1, b = 5
  // fun = (x1, x2) => x1 + x2
  //functionOnRange == 1 + 2 + 3 + 4 + 5
  def aggOnRange(a: Int, b: Int, fun: (Int, Int) => Int): Int =
    if (a == b) a
    else if (a < b) fun(a, aggOnRange(a + 1, b, fun))
    else fun(b, aggOnRange(a, b + 1, fun))

  val min = 1
  val max = 3

  val ans = aggOnRange(min, max, (x, y) => x + y)

//  println(ans)

  //Exercise
  //Reverse the order of the elements in the list
  def reverseList(list: List[Int]): List[Int] = list match {
    case Nil     => Nil
    case x :: xs => reverseList(xs) :+ x
  }

  println(reverseList(List(1, 2, 3, 4)))

  //MEGA BONUS
  //Implement a function that calculates all the possible combinations of a list with chars
  //For example
  //List(a,b,c)
  //List(List(a,b,c),List(a,b),List(a,c),List(a),List(b,c),List(b),List(c),List())

  //Keep in mind this is my solution you might code better one
  def combinationsCal(charList: List[Char]): List[List[Char]] = {

    def recursionCombin(list: List[Char]): Set[List[Char]] = {
      list match {
        case Nil         => Set(List())
        case head :: Nil => Set(List(head)) ++ recursionCombin(Nil)
        case head :: second :: tail =>
          (Set(list) ++ recursionCombin(head :: tail)) ++
            recursionCombin(second :: tail) ++
            recursionCombin(head :: list.tail.dropRight(1))
      }
    }

    recursionCombin(charList).toList
  }

  val comb = combinationsCal(List('a', 'b', 'c'))
//  println(comb + "  " + comb.size)
}

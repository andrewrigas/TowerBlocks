package foundations.Solutions.B3.RecursionFun

import foundations.Exercises.B4.AlgebraicDataTypes.AlgebraicDataTypes.Tree
import foundations.Exercises.B4.AlgebraicDataTypes.AlgebraicDataTypes.Tree._

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
  val list = List(1, 2, 3, 4)
//  println(reverseList(list))

  //Exercise
  //Implement the map higher order function
  //Recursive solution
  def map(list: List[Int], f: Int => Int): List[Int] = list match {
    case Nil     => Nil
    case x :: xs => f(x) :: map(xs, f)
  }

  //Exercise
  //Implement the flatMap higher order function
  //Recursive solution
  def flatMap(list: List[Int], f: Int => List[Int]): List[Int] = list match {
    case Nil     => Nil
    case x :: xs => f(x) ++ flatMap(xs, f)
  }

  println(flatMap(list, x => List(x, x + 1)))

  //Exercise
  //Implement foldLeft tail recursive
  //Apply a function on each element of list with the acc
  //For example
  //List(1,2,3,4), acc = 0
  //acc = f(0,1)
  //acc = f(f(0,1),2)
  //etc
  def foldLeft(list: List[Int])(acc: Int)(f: (Int,Int) => Int): Int = list match {
    case Nil => acc
    case x :: xs => foldLeft(xs)(f(acc,x))(f)
  }

  //Exercise
  //Apply a Lambda/Anonymous function on the leafs
  //For Example
  // Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))
  // x => x + 1
  // Res: Branch(Branch(Leaf(1+1),Leaf(2+1)),Leaf(3+1)
  def mapOnTree(tree: Tree[Int], f: Int => Int): Tree[Int] = tree match {
    case Leaf(value) => Leaf(f(value))
    case Branch(leftTree, rightTree) =>
      Branch(mapOnTree(leftTree, f), mapOnTree(rightTree, f))
  }

  val tree1: Tree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Leaf(4))
  val tree2: Tree[Int] = Branch(
    Branch(Branch(Leaf(1), Leaf(2)), Leaf(4)),
    Branch(Branch(Branch(Leaf(3), Branch(Leaf(2), Leaf(1))), Leaf(2)), Leaf(4))
  )

  println(mapOnTree(tree1, x => x + 1))
  println(mapOnTree(tree2, x => x + 1))

  //Bonus
  //Count how many different ways you can make change for an amount,
  // given a list of coin denominations.
  // For example:
  // There are 3 ways to give change for 4
  // if you have coins with denomination 1 and 2:
  // 1+1+1+1, 1+1+2, 2+2. total 3
  def countChange(money: Int, coins: List[Int]): Int =
    if (money == 0) 1
    else if (money < 0) 0
    else
      coins match {
        case Nil => 0
        case head :: tail =>
          countChange(money - head, coins) + countChange(money, tail)
      }

  println(countChange(4, list))

  def countChangeGetList(money: Int,
                         coins: List[Int],
                         acc: List[Int] = Nil): List[List[Int]] =
    if (money == 0) List(acc)
    else if (money < 0) Nil
    else
      coins match {
        case Nil => Nil
        case head :: tail =>
          countChangeGetList(money - head, coins, head :: acc) ++
            countChangeGetList(money, tail, acc)
      }

  println(countChangeGetList(4, list))

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

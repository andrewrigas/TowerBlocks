package foundations.Exercises.B3.RecursionFun

import foundations.Exercises.B4.AlgebraicDataTypes.AlgebraicDataTypes.Tree
import foundations.Exercises.B4.AlgebraicDataTypes.AlgebraicDataTypes.Tree._

object RecursionFun extends App {

//  A tail recursion is also a kind of recursion
//  but it will make the return value of the recursion
//  call as the last statement of the method

  @scala.annotation.tailrec
  def greatestCommonDivisor(a: Int, b: Int): Int =
    if (b == 0) a else greatestCommonDivisor(b, a % b)

  //Return a sequence of results that will be calculated at the end
  def triangular(a: Int): Int =
    if (a <= 0) 0 else a + triangular(a - 1)

  //Exercises
  //Calculate Factorial recursively
  //For example:
  // n = 4
  // factorial == 4! => 4 * 3 * 2 * 1
  def factorial(n: Int): Int = ???

  //Exercise
  //Implement factorial with tail recursion
  def factoriaTailRec(n: Int,acc: Int): Int = ???

  //Nested Function
  def nestedFunction(list: List[Int]): Int = {

    def foldRight(l: List[Int], acc: Int = 0): Int = l match {
      case Nil     => acc
      case x :: xs => foldRight(xs, acc + x)
    }

    foldRight(list)
  }

  def parenthesisBalance(chars: List[Char]): Boolean = ???

  //Higher-Order Functions
  def hashString(str: String,
                 check: String => Boolean,
                 fun: String => String): String =
    if (check(str)) str else fun(str)

  //Exercise
  //Apply factorial on a range of values and aggregate them
  //For example:
  //a = 1, b = 5
  //Aggregation f1 + f2
  //aggFactorialRange == 1! + 2! + 3! + 4! + 5!
  def aggFactorialRange(a: Int, b: Int): Int = ???

  //Exercise
  //Apply a function on a range of two values
  //For example:
  // a = 1, b = 5
  // fun = (x1, x2) => x1 + x2
  //functionOnRange == 1 + 2 + 3 + 4 + 5
  def aggOnRange(a: Int, b: Int, fun: (Int, Int) => Int): Int = ???

  //Collections Rec
  def sumOfallElements1(list: List[Int], acc: Int = 0): Int =
    if (list.isEmpty) acc else sumOfallElements1(list.tail, list.head + acc)

  //With Pattern Matching
  def sumOfallElements2(list: List[Int]): Int = list match {
    case Nil          => 0
    case head :: tail => head + sumOfallElements2(tail)
  }

  //Exercise
  //Reverse the order of the elements in the list
  def reverseList(list: List[Int]): List[Int] = ???

  //Exercise
  //Implement the map higher order function
  //Recursive solution
  def map(list: List[Int], f: Int => Int): List[Int] = ???

  //Exercise
  //Implement the flatMap higher order function
  //Recursive solution
  def flatMap(list: List[Int], f: Int => List[Int]): List[Int] = ???

  val tree1: Tree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Leaf(4))
  val tree2: Tree[Int] = Branch(
    Branch(Branch(Leaf(1), Leaf(2)), Leaf(4)),
    Branch(Branch(Branch(Leaf(3), Branch(Leaf(2), Leaf(1))), Leaf(2)), Leaf(4))
  )

  def calculateSumOfTheTree(tree: Tree[Int]): Int = tree match {
    case Leaf(value) => value
    case Branch(leftTree, rightTree) =>
      calculateSumOfTheTree(leftTree) + calculateSumOfTheTree(rightTree)
  }

  //Exercise
  //Apply a Lambda/Anonymous function on the leafs
  //For Example
  // Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))
  // x => x + 1
  // Res: Branch(Branch(Leaf(1+1),Leaf(2+1)),Leaf(3+1)
  def mapOnTree(tree: Tree[Int], f: Int => Int): Tree[Int] = ???

  //Bonus
  //Count how many different ways you can make change for an amount,
  // given a list of coin denominations.
  // For example:
  // There are 3 ways to give change for 4
  // if you have coins with denomination 1 and 2:
  // 1+1+1+1, 1+1+2, 2+2.
  def countChange(money: Int, coins: List[Int]): Int = ???

  //MEGA BONUS
  //Implement a function that calculates all the possible combinations of a list with chars
  //For example
  //List(a,b,c)
  //List(List(a,b,c),List(a,b),List(a,c),List(a),List(b,c),List(b),List(c),List()) Empty list is a combination as well
  def combinationsCal(list: List[Char]): List[List[Char]] = ???

  val comb = combinationsCal(List('a', 'b', 'c'))
  //println(comb + "  " + comb.size)

}

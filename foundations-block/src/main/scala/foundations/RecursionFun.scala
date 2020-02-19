package foundations

import foundations.AlgebraicDataTypes.Tree._
import foundations.AlgebraicDataTypes._

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
  //Apply a function on a range of two values
  //For example:
  // a = 1, b = 5
  // fun = (x1, x2) => x1 + x2
  //functionOnRange == 1 + 2 + 3 + 4 + 5
  def aggOnRange(a: Int, b: Int, fun: (Int, Int) => Int): Int = ???

  //Exercise
  //Apply factorial on a range of values and aggregate them
  //For example:
  //a = 1, b = 5
  //Aggregation f1 + f2
  //aggFactorialRange == 1! + 2! + 3! + 4! + 5!
  def aggFactorialRange(a: Int, b: Int): Int = ???

  //Collections Rec
  def sumOfallElements1(list: List[Int], acc: Int): Int = ???

  def sumOfallElements2(list: List[Int]): Int = ???

  def reverseList(list: List[Int]): List[Int] = ???

  def addToTheBottom(element: Int, list: List[Int]): List[Int] = ???

  val tree1: Tree[Int] = Branch(Branch(Leaf(1), Leaf(2)), Leaf(4))
//  val tree2: Tree[Int] = Branch(Branch(Branch(Leaf(1),Leaf(2)),Leaf(4)),Branch(Branch(Branch(Leaf(3),Branch(Leaf(2),Leaf(1))),Leaf(2)),Leaf(4)))

  def calculateSumOfTheTree(tree: Tree[Int]): Int = tree match {
    case Leaf(value) => value
    case Branch(leftTree, rightTree) =>
      calculateSumOfTheTree(leftTree) + calculateSumOfTheTree(rightTree)
  }

  def mapOnTree[A, B](tree: Tree[A], f: A => B): Tree[B] = ???

}
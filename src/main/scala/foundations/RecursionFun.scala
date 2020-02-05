package foundations

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
  def factorial(n: Int): Int = ???

  def parenthesisBalance(chars: List[Char]): Boolean = ???

  //Higher-Order Functions
  def hashString(str: String,
                 check: String => Boolean,
                 fun: String => String): String =
    if (check(str)) str else fun(str)

  def sumOfIntRange(a: Int, b: Int, fun: Int => Int): Int = {
    if (a > b) 0
    else fun(a) + sumOfIntRange(a + 1, b, fun)
  }

  def sumOfFactorialRange(a: Int, b: Int): Int = ???

  //Collections Rec
  def sumOfallElements1(list: List[Int], acc: Int): Int = ???

  def sumOfallElements2(list: List[Int]): Int = ???

}

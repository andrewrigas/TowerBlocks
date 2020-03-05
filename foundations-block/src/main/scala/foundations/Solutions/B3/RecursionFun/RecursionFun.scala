package foundations.Solutions.B3.RecursionFun

object RecursionFun extends App {

  //MEGA BONUS
  //Implement a function that calculates all the possible combinations of a list with chars
  //For example
  //List(a,b,c)
  //List(List(a,b,c),List(a,b),List(a,c),List(a),List(b,c),List(b),List(c),List())

  def combinationsCal(list: List[Char]): List[List[Char]] = {

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

    recursionCombin(list).toList
  }

  val comb = combinationsCal(List('a', 'b', 'c'))
  println(comb + "  " + comb.size)
}

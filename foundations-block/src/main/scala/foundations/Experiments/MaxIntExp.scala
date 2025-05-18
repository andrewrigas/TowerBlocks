package foundations.Experiments

object MaxIntExp extends App {

  val minValue: Int = Int.MinValue
  val maxValue: Int = Int.MaxValue

  println(s"Minimum Int value: $minValue")
  println(s"Maximum Int value: $maxValue")

  println(s"Minimum Int value - 1: ${minValue - 1}")
  println(s"Maximum Int value + 1: ${maxValue + 1}")

  println(s"(Maximum Int value + 1) == Minimum Int value =  ${(maxValue + 1) == minValue}")

}

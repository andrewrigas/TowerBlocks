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
  //  val f211: TypeAlias = ???

  //println(s"f311 = ${f311(1)}")

  //println(s"f321 = ${f321(1)}")

  //Higher Order Function
  def funF(x: Int, f: Int => Int): Int = f(x)

  //  println(funF(5 , x => x + 1))
  //  println(funF(5 , f11))

  //Exercises
  //Create raw functions that takes 2 parameters and perform an operation
  //  val sum = ???
  //  val subtrahend = ???
  //  val product = ???
  //  val quotient = ???

  //Create a higher order function that has input of 2 Parameters an then pass one of the above operations
  //  def calculator = ???

  //println(s"Addition: ")
  //println(s"Subtraction:")
  //println()
  //println()

  //Pattern Matching
  def optionExample(maybeInt: Option[Int]): Int = maybeInt match {
    case Some(value) => value
    case None => 0
  }

  //Exercise
  //Apply a Lambda/Anonymous Function on an Int and return Option
  def flatMapOption(maybeInt: Option[Int],
                    fun: Int => Option[Int]): Option[Int] = ???


  def listExample(list: List[Int]) = list match {
    case Nil => 0
    case head :: tail => head
  }

  //Pattern Matching with order
  def listExample2(list: List[Int]): Int = list match {
    case Nil => 0
    case head :: second :: tail => head
    case head :: tail => head
  }

  //Exercise
  //Higher Order Function
  //Apply a lambda function on the first 2 elements in a list
  def listSum2RandomElements(list: List[Int],
                             f: (Int, Int) => Int): Option[Int] = ???


  //Function Composition
  //Composing methods works only with vals
  val addOne: Int => Int = (x: Int) => x + 1
  val multiplyByTow: Int => Int = (x: Int) => x * 2

  //compose
  //compose makes a new function that composes other functions addOne(multiplyByTow(x))
  val composeExample: Int => Int = addOne compose multiplyByTow
  val resCompose = composeExample(2)
  //  println(s"Result andThen:  $resCompose")

  //andThen
  //andThen is like compose, but calls the first function and then the second, multiplyByTow(addOne(x))
  val andThenExample: Int => Int = addOne andThen multiplyByTow
  val resAndThen = andThenExample(2)

  //  println(s"Result andThen:  $resAndThen")


  //Compose 2 or more functions together
  final case class Item(name: String, price: Double)


  def applyDiscount(itemsList: List[Item], discount: Double): Double = {

    //Create a lambda function that extracts the value
    val getPrice: Item => Double = (item: Item) => item.price
    //Create a lambda function that calculates the discount
    val applyDiscount: Double => Double = (price: Double) => price * discount

    //In scala instead of saying getPrice.andThen(applyDiscount) you can use spaces
    //andThen solution
    itemsList.map(item => (getPrice andThen applyDiscount) (item)).sum

    //compose solution
    itemsList.map(applyDiscount compose getPrice).sum
  }

  //Exercise
  //A Clothes Store is trying to calculate
  //The total price that of their stock
  //With and without the profit

  sealed abstract class Clothes {
    def price: Double
    def quantity: Int
    def profit: Double
  }

  case object Jumper extends Clothes {
    val price: Double = 45.00
    val quantity: Int = 17
    val profit: Double = 0.40
  }

  case object Shirt extends Clothes {
    val price: Double = 49.99
    val quantity: Int = 13
    val profit: Double = 0.35
  }

  case object Trouser extends Clothes {
    val price: Double = 30.00
    val quantity: Int = 24
    val profit: Double = 0.35
  }

  def getProfit(stock: List[Clothes]): (Double, Double) = {

    //Create Type alias for tuples
    //After you solve this exercise try remove the type alias and replace the rest
    // of the types with the actual tuple and see what happens
    type PriceQuantity = (Double, Int)
    type PriceQuantityProfit = (Double, Int, Double)

    val getPriceQuantity: Clothes => PriceQuantity = ???
    val getPriceQuantityProfit: Clothes => PriceQuantityProfit = ???

    //When you replace the type with tuple you will need to modify this function parameters
    val calTotalPrice: PriceQuantity => Double = (priceQuantity: PriceQuantity) => ???
    val calTotalPriceProfit: PriceQuantityProfit => Double = (priceQuantityProfit: PriceQuantityProfit) => ???

    //Use function composition to solve this
    //After you replace type alias with tuples you should get something interesting here
    //can you spot what is the problem ??
    val getTotalPriceWithOutProfit: Clothes => Double = ???
    val getTotalPriceWithProfit: Clothes => Double = ???

    val totalPrices: List[(Double, Double)] = ???

    ???
  }

  //Bonus Exercise
  //We want to split a configuration in to Key Value pairs in a Map collection
  //The problem is that we might have a nested Key value pairs inside the values
  //We should ended up with Map(key -> Map(key -> value))
  //where key is the spark key and value is the spark key value pair
  //if the value contains only single value then key should be the same with spark key
  //For Example
  //Map(
  // --spark.driver.cores -> Map(--spark.driver.cores -> 2),
  // --spark.kafka.client.properties -> Map(security.protocol -> SSL, security.password -> 1234)
  // )
  val sparkConfig =
    """--spark.driver.cores=2
      |--spark.driver.memory=2g
      |--spark.executor.cores=4
      |--spark.executor.memory=3g
      |--spark.executor.instances=1
      |--spark.kafka.producer.brokers=localhost:8080
      |--spark.kafka.producer.schemaRegistry=localhost:9090
      |--spark.kafka.client.properties=security.protocol=SSL,security.password=1234
      |""".stripMargin

  def getSparkConfig(rawSparkConfig: String): Map[String,Map[String,String]] = {

    //Hint: you might want to use split function
    //https://alvinalexander.com/scala/how-to-split-csv-strings-in-scala-cookbook/
    //https://stackoverflow.com/questions/18462826/split-string-only-on-first-instance-java

    val splitLineBreaks: String => Array[String] = ???

    val splitConfigKeyValues: Array[String] => Map[String, String] = ???

    val splitValueToKeyValues: Map[String,String] => Map[String,Map[String,String]] = ???

    ???
  }

  println(getSparkConfig(sparkConfig))

}

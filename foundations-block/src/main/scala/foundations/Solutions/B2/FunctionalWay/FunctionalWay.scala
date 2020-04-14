package foundations.Solutions.B2.FunctionalWay

object FunctionalWay extends App {

  //Exercise
  //Write a function that takes a Tuple of Strings and 2 Integers
  //Sum the 2 Integers Together
  //Concat the Tuple Strings with Sum return a String
  //For Example
  //f211(("Hello","World"), 1 , 2) = "HelloWorld3"

  val f211: (String,String) => Int => Int => String = (word1,word2) => x1 => x2 => s"$word1$word2${x1+x2}"

  //Exercises
  //Create raw functions that takes 2 parameters and perform an operation
  val sum: (Int, Int) => Int = (x, y) => x + y
  val subtrahend: (Int, Int) => Int = (x, y) => x + y
  val product: (Int, Int) => Int = (x, y) => x * y
  val quotient: (Int, Int) => Int = (x, y) => x / y

  //Create a function that has input of 2 Parameters and pass the above functions Type to perform the operation
  def calculator(x: Int, y: Int)(f: (Int, Int) => Int): Int = f(x, y)

  //Exercise
  //Apply a Lambda/Anonymous Function on an Int and return Option
  def flatMapOption(maybeInt: Option[Int],
                    fun: Int => Option[Int]): Option[Int] = maybeInt match {
    case Some(value) => fun(value)
    case None        => None
  }

  //Exercise
  //Higher Order Function
  //Apply a Lambda/Anonymous Function on the first 2 elements in a list
  def listSum2RandomElements(list: List[Int],
                             f: (Int, Int) => Int): Option[Int] = list match {
    case _                      => None
    case head :: second :: tail => Some(f(head, second))
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
    type PriceQuantity = (Double, Int)
    type PriceQuantityProfit = (Double, Int, Double)

    val getPriceQuantity: Clothes => PriceQuantity = (cl: Clothes) => (cl.price,cl.quantity)
    val getPriceQuantityProfit: Clothes => PriceQuantityProfit = (cl: Clothes) => (cl.price,cl.quantity,cl.profit)

    val calTotalPrice: PriceQuantity => Double = (priceQuantity: PriceQuantity) => priceQuantity._1 * priceQuantity._2
    val calTotalPriceProfit: PriceQuantityProfit => Double = (priceQuantityProfit: PriceQuantityProfit) => priceQuantityProfit._1 * priceQuantityProfit._2 * priceQuantityProfit._3

    //Use function composition to solve this
    val getTotalPriceWithOutProfit: Clothes => Double = getPriceQuantity andThen calTotalPrice
    val getTotalPriceWithProfit: Clothes => Double = getPriceQuantityProfit  andThen calTotalPriceProfit

    val totalPrices: List[(Double, Double)] = stock.map(cl => (getTotalPriceWithOutProfit(cl) ,getTotalPriceWithProfit(cl)))

    (totalPrices.map(_._1).sum,totalPrices.map(_._2).sum)
  }

  //Tuple problem
  def getProfitTuple(stock: List[Clothes]): (Double, Double) = {

    //This functions return a tuple which is a single argument and you can access it with _.1, _.2 etc.
    val getPriceQuantity: Clothes => (Double, Int) = (cl: Clothes) => (cl.price,cl.quantity)
    val getPriceQuantityProfit: Clothes => (Double, Int, Double) = (cl: Clothes) => (cl.price,cl.quantity,cl.profit)

    //First Solution
    //This functions should be getting a tuple as a single argument and not as two separated arguments
    //Second  parenthesis added ()
    val calTotalPrice: ((Double, Int)) => Double = (tuple: (Double, Int)) => tuple._1 * tuple._2
    //Second Solution
    //Keep the definition of the function as it is and ****
    val calTotalPriceProfit: (Double, Int, Double) => Double = (price: Double, quantity: Int, profit: Double) => price * quantity * profit

    //Use function composition to solve this
    //To be able to solve this with tuples we have to transform the input of the callTotal* into tupled Object
    // to pass a single argument
    val getTotalPriceWithOutProfit: Clothes => Double = getPriceQuantity andThen calTotalPrice

    //****
    //Transform our callTotalPriceProfit function definition to get a tuple of arguments with .tupled
    val getTotalPriceWithProfit: Clothes => Double = getPriceQuantityProfit.andThen(calTotalPriceProfit.tupled)

    val totalPrices: List[(Double, Double)] = stock.map(cl => (getTotalPriceWithOutProfit(cl) ,getTotalPriceWithProfit(cl)))

    (totalPrices.map(_._1).sum,totalPrices.map(_._2).sum)
  }

  val stock = List(Jumper,Shirt,Trouser)
//  println(getProfit(stock))
//  println(getProfitTuple(stock))

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

    val splitLineBreaks: String => Array[String] = (str: String) => str.trim.split("\n")

    val splitConfigKeyValues: Array[String] => Map[String, String] = (strList: Array[String]) => {
      strList.flatMap(str => {
        str.trim.split("=", 2) match {
          case Array(key, value) => Some((key, value))
          case _ => None
        }
      }).toMap
    }

    val splitValueToKeyValues: Map[String,String] => Map[String,Map[String,String]] = (strMap: Map[String,String]) => {
      strMap.flatMap( pair => {
        val newValue = splitConfigKeyValues(pair._2.trim.split(",")) match {
          case emptyMap if emptyMap.isEmpty => Map(pair)
          case map => map
        }
        Map(pair._1 -> newValue)
      })
    }

    (splitLineBreaks andThen splitConfigKeyValues andThen splitValueToKeyValues)(rawSparkConfig)
  }

//  println(getSparkConfig(sparkConfig))
}

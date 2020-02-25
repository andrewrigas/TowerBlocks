package foundations

import foundations.AdHocPolymorphism.Human.{Maria, Trump}

object AdHocPolymorphism extends App {

  implicit val ImplicitInt = 10

  def funcConcatStringInt(str: String)(implicit value: Int): String = {
    str + value
  }

  sealed abstract class Human {
    def name: String
  }

  object Human {
    final case class Maria() extends Human {
      val name: String = "G. Maria"
    }

    final case class Trump() extends Human {
      val name: String = "D. Trump"
    }
  }

  trait TypeClass[A] {
    def whatIsMyType: String
  }

  object TypeClass {
    import Human._

    implicit val typeString = new TypeClass[String] {
      val whatIsMyType: String = "String"
    }

    implicit val typeInt = new TypeClass[Int] {
      val whatIsMyType: String = "Int"
    }

    implicit val typeBool = new TypeClass[Boolean] {
      val whatIsMyType: String = "Bool"
    }

    implicit val typeTrump = new TypeClass[Trump] {
      val whatIsMyType: String = "Trump"
    }

    implicit val typeMaria = new TypeClass[Maria] {
      val whatIsMyType: String = "Maria"
    }
  }

  def getTheType[A](implicit typeClass: TypeClass[A]): String = {
    typeClass.whatIsMyType
  }

  println(getTheType[String])
  println(getTheType[Int])
  println(getTheType[Boolean])

  trait Food[A] {
    def iAte: String
  }

  object Food {

    implicit val iAteMaria = new Food[Maria] {
      val iAte: String = "Steak"
    }

    implicit val iAteTrump = new Food[Trump] {
      val iAte: String = "Poop"
    }
  }

  def whoAmIwhatDidIAte[A](implicit food: Food[A],
                           typeClass: TypeClass[A]): String = {
    "My name is " + getTheType[A] + " and I ate " + food.iAte
  }

  println(whoAmIwhatDidIAte[Maria])
  println(whoAmIwhatDidIAte[Trump])

}

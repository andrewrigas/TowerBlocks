package foundations.Exercises.B5.AdHocPolymorphism

import AdHocPolymorphism.Human.{Maria, Trump}

object AdHocPolymorphism extends App {

  implicit val ImplicitInt = 10

  def funcConcatStringInt(str: String)(implicit value: Int): String = {
    str + value
  }

  println(funcConcatStringInt("Ball "))

  implicit val maybeInt: Option[Int] = Some(3)

  def funcGetOptionImplicit(value: Int)(implicit maybeInt: Option[Int]): Int =
    maybeInt match {
      case Some(suc) => suc + value
      case None      => value
    }

//  println(funcGetOptionImplicit(3))

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

  //Associate functionality with types
  trait Decoder[A] { self =>
    def decodeType: String
  }

  object Decoder {
    import Human._

    implicit val typeString = new Decoder[String] {
      val decodeType: String = "String"
    }

    implicit val typeInt = new Decoder[Int] {
      val decodeType: String = "Int"
    }

    implicit val typeBool = new Decoder[Boolean] {
      val decodeType: String = "Bool"
    }

    implicit val typeTrump = new Decoder[Trump] {
      val decodeType: String = "Trump"
    }

    implicit val typeMaria = new Decoder[Maria] {
      val decodeType: String = "Maria"
    }
  }

  def getTheType[A](implicit typeClass: Decoder[A]): String = {
    typeClass.decodeType
  }

//  println(getTheType[String])
//  println(getTheType[Int])
//  println(getTheType[Boolean])

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
                           typeClass: Decoder[A]): String = {
    "My name is " + getTheType[A] + " and I ate " + food.iAte
  }

//  println(whoAmIwhatDidIAte[Maria])
//  println(whoAmIwhatDidIAte[Trump])

  //Create an Ad Hoc Polymorphism

}

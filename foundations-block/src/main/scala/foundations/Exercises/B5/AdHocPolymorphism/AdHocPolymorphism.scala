package foundations.Exercises.B5.AdHocPolymorphism

import foundations.Exercises.B5.AdHocPolymorphism.AdHocPolymorphism.Human.{Maria, Trump}

object AdHocPolymorphism extends App {

  given Int = 10

  def funcConcatStringInt(str: String)(implicit value: Int): String =
    str + value

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

  // Associate functionality with types
  trait Decoder[A] { self =>
    def decodeType: String
  }

  object Decoder {

    given typeString: Decoder[String] = new Decoder[String] {
      val decodeType: String = "String"
    }

    given typeInt: Decoder[Int] = new Decoder[Int] {
      val decodeType: String = "Int"
    }

    given typeBool: Decoder[Boolean] = new Decoder[Boolean] {
      val decodeType: String = "Bool"
    }

    given typeTrump: Decoder[Trump] = new Decoder[Trump] {
      val decodeType: String = "Trump"
    }

    given typeMaria: Decoder[Maria] = new Decoder[Maria] {
      val decodeType: String = "Maria"
    }
  }

  def getTheType[A](implicit typeClass: Decoder[A]): String =
    typeClass.decodeType

//  println(getTheType[String])
//  println(getTheType[Int])
//  println(getTheType[Boolean])

  trait Food[A] {
    def iAte: String
  }

  object Food {

    given iAteMaria: Food[Maria] = new Food[Maria] {
      val iAte: String = "Steak"
    }

    given iAteTrump: Food[Trump] = new Food[Trump] {
      val iAte: String = "Poop"
    }
  }

  def whoAmIwhatDidIAte[A](implicit food: Food[A], typeClass: Decoder[A]): String =
    "My name is " + getTheType[A] + " and I ate " + food.iAte

//  println(whoAmIwhatDidIAte[Maria])
//  println(whoAmIwhatDidIAte[Trump])

  // Create an Ad Hoc Polymorphism

}

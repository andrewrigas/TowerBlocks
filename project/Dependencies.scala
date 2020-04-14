import sbt._

object Dependencies {

  object Versions {
    //Runtime Dependencies
    val scalaLoggingVersion = "3.9.2"
    val slf4jVersion = "1.7.30"
    val pureConfigVersion = "0.12.3"
    //Test Dependencies
    val scalaTestVersion = "3.1.0"
    val scalaCheckVersion = "1.14.1"
  }

  import Versions._

  lazy val foundationsBlockLib = commonLib

  lazy val servicesBlockLib = commonLib ++ Seq(
    "com.github.pureconfig" %% "pureconfig" % pureConfigVersion
  )

  private lazy val commonLib = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
    "org.slf4j" % "slf4j-simple" % slf4jVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test", //test means that those lib will be only use in the /test file path
    "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test", // which is our test code and they will not be downloaded or used for the actual application
  )

}

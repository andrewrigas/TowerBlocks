import sbt.Keys.libraryDependencies
import sbt._

object Dependencies {

  object Versions {
    //Runtime Dependencies
    val scalaLogging = "3.9.2"
    val slf4j = "1.7.30"
    val pureConfig = "0.12.3"
    val scalaParallelCollection = "0.2.0"
    //Test Dependencies
    val scalaTest = "3.1.0"
    val scalaCheck = "1.14.1"
  }

  object Libraries {
    //Runtime Dependencies
    val scalaParallelCollections = "org.scala-lang.modules" %% "scala-parallel-collections" % Versions.scalaParallelCollection
    val pureConfig = "com.github.pureconfig" %% "pureconfig" % Versions.pureConfig
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging
    val sl4j = "org.slf4j" % "slf4j-simple" % Versions.slf4j
    //Test Dependencies
    val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest //test means that those lib will be only use in the /test file path
    val scalaCheck = "org.scalacheck" %% "scalacheck" % Versions.scalaCheck // which is our test code and they will not be downloaded or used for the actual application
  }

  lazy val foundationsBlock = libraryDependencies ++= test ++ Seq(
    Libraries.scalaParallelCollections
  )

  lazy val servicesBlock = libraryDependencies ++=  test ++ commonLib

  lazy val fileWriterBlock = libraryDependencies ++= test ++ commonLib

  private lazy val commonLib = Seq(
    Libraries.pureConfig,
    Libraries.scalaLogging,
    Libraries.sl4j
  )

  private lazy val test = Seq(
    Libraries.scalaCheck % "test",
    Libraries.scalaTest % "test"
  )

}

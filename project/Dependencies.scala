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

  lazy val foundationsBlockLib = libraryDependencies ++= commonLib ++ Seq(
      "org.scala-lang.modules" %% "scala-parallel-collections" % Versions.scalaParallelCollection
  )

  lazy val servicesBlockLib = libraryDependencies ++=  commonLib ++ Seq(
    "com.github.pureconfig" %% "pureconfig" % Versions.pureConfig
  )

  private lazy val commonLib = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging,
    "org.slf4j" % "slf4j-simple" % Versions.slf4j,
    "org.scalatest" %% "scalatest" % Versions.scalaTest % "test", //test means that those lib will be only use in the /test file path
    "org.scalacheck" %% "scalacheck" % Versions.scalaCheck % "test", // which is our test code and they will not be downloaded or used for the actual application
  )

}

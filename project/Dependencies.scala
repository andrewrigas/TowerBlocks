import sbt._

object Dependencies {

  lazy val foundationsBlockLib = commonLib

  lazy val servicesBlockLib = commonLib

  private lazy val commonLib = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "org.scalatest" %% "scalatest" % "3.1.0" % "test", //test means that those lib will be only use in the /test file path
    "org.scalacheck" %% "scalacheck" % "1.14.1" % "test", // which is our test code and they will not be downloaded or used for the actual application
  )

}

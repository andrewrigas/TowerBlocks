import sbt._

object Dependencies {

  lazy val foundationsBlockLib = commonLib

  private lazy val commonLib = Seq(
    "org.scalatest" %% "scalatest" % "3.1.0" % "test",
    "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"
  )

}

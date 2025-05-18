import sbt.*

object Dependencies {

  lazy val scalaLoggingV            = "3.9.5"
  lazy val slf4jV                   = "2.0.17"
  lazy val pureConfigV              = "0.17.9"
  lazy val scalaParallelCollectionV = "1.2.0"
  lazy val scalaTestV               = "3.2.19"
  lazy val scalaCheckV              = "1.18.1"

  // Utils
  lazy val scalaParallelCollections = "org.scala-lang.modules" %% "scala-parallel-collections" % scalaParallelCollectionV
  lazy val pureConfig               = "com.github.pureconfig" %% "pureconfig-core" % pureConfigV

  // Logging
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingV
  lazy val sl4j         = "org.slf4j"                   % "slf4j-simple"  % slf4jV

  // Test
  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestV
  lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % scalaCheckV
}

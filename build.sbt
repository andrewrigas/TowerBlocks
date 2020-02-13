import scala.sys.process._

name := "TowerBlocks"
scalaVersion := "2.13.1"

version in ThisBuild := "git rev-parse --short HEAD".!!.trim

lazy val towerBlocks = Projects.towerBlocks
  .settings(libraryDependencies ++= Dependencies.TowerBlocksLib)

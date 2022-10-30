import scala.sys.process._

//in This Build so all the Modules get same setting
//try removing in ThisBuild run sbt and execute 'version' or 'scalaVersion'
ThisBuild / name := "TowerBlocks"
ThisBuild / scalaVersion := "2.13.10"
ThisBuild / version := "git rev-parse --short HEAD".!!.trim

lazy val towerBlocks = Projects.root
  .aggregate(modules: _*)

lazy val foundationsBlock = Projects.foundationsBlock
  .settings(Dependencies.foundationsBlock)
  .dependsOn(servicesBlock)

lazy val fileWriterBlock = Projects.fileWriterBlock
  .settings(Dependencies.fileWriterBlock)

lazy val servicesBlock = Projects.servicesBlock
  .settings(Dependencies.servicesBlock)

lazy val modules: Seq[ProjectReference] = Seq(foundationsBlock, servicesBlock, fileWriterBlock)

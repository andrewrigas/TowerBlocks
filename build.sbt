name := "TowerBlocks"
scalaVersion := "2.13.1"
version := "1.0"

lazy val towerBlocks = Projects.towerBlocks
  .aggregate(modules: _*)

lazy val foundationsBlock = Projects.foundationsBlock
  .settings(parallelExecution := false)
  .settings(libraryDependencies ++= Dependencies.foundationsBlockLib)

lazy val servicesBlock = Projects.servicesBlock
  .settings(parallelExecution := false)
  .settings(libraryDependencies ++= Dependencies.servicesBlockLib)

lazy val modules: Seq[ProjectReference] = Seq(foundationsBlock, servicesBlock)

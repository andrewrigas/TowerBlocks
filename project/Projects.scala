import sbt._

object Projects {

  lazy val towerBlocks = Project(id = "TowerBlocks", base = file("."))

  lazy val foundationsBlock =
    Project(id = "FoundationsBlock", base = file("foundations-block"))

}

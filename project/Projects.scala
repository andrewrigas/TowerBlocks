import sbt._

object Projects {

  def createProject(name: String,baseFile: String) = {
    Project(id = name, base = file(baseFile))
      //.settings(ScalaOptions.compileTimeOptions)
  }

  lazy val towerBlocks = createProject(name = "TowerBlocks",baseFile = ".")

  lazy val foundationsBlock = createProject(name = "FoundationsBlock",baseFile = "foundations-block")

  lazy val servicesBlock = createProject(name = "ServicesBlock",baseFile = "services-block")
}

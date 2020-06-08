import sbt._

object Projects {

  def createProject(name: String,baseFile: String): Project =
    Project(id = name, base = file(baseFile))
//      .settings(ScalaOptions.inCompile)

  lazy val root = Project("TowerBlocks",file("."))

  lazy val foundationsBlock = createProject(name = "FoundationsBlock",baseFile = "foundations-block")

  lazy val servicesBlock = createProject(name = "ServicesBlock",baseFile = "services-block")

  lazy val fileWriterBlock = createProject(name = "FileWriter",baseFile = "file-writer-block")
}

import scala.sys.process.*

lazy val enableScalaLint = sys.env.getOrElse("ENABLE_SCALA_LINT_ON_COMPILE", "true").toBoolean

// `Global` applies to modules (parent and child modules) the same settings
Global / onChangedBuildSource := ReloadOnSourceChanges

// `ThisBuild` applies to modules (only child modules) the same settings
ThisBuild / scalaVersion              := "3.7.0"
ThisBuild / version                   := "git rev-parse --short HEAD".!!.trim
ThisBuild / scalafixOnCompile         := enableScalaLint
ThisBuild / scalafmtOnCompile         := enableScalaLint
ThisBuild / semanticdbVersion         := scalafixSemanticdb.revision
ThisBuild / semanticdbEnabled         := true
ThisBuild / Test / fork               := true
ThisBuild / run / fork                := true
ThisBuild / Test / parallelExecution  := true
ThisBuild / Test / testForkedParallel := true

def createProject(name: String, baseFile: String): Project =
  Project(id = name, base = file(baseFile))
    .settings(Settings.ScalaCompiler)
    .settings(Aliases.all)

lazy val towerBlocks = Project("TowerBlocks", file("."))
  .aggregate(modules*)

lazy val foundationsBlock = createProject(name = "FoundationsBlock", baseFile = "foundations-block")
  .dependsOn(servicesBlock)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.scalaParallelCollections,
      Dependencies.pureConfig,
      Dependencies.scalaLogging,
      Dependencies.sl4j,
      Dependencies.scalaTest % Test, // 'Test' means that those deps will be only used in the test scope (`/test` file path)
      Dependencies.scalaCheck % Test,
    )
  )

lazy val fileWriterBlock = createProject(name = "FileWriter", baseFile = "file-writer-block")
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.scalaParallelCollections,
      Dependencies.pureConfig,
      Dependencies.scalaLogging,
      Dependencies.sl4j,
      Dependencies.scalaTest % Test, // 'Test' means that those deps will be only used in the test scope (`/test` file path)
      Dependencies.scalaCheck % Test,
    )
  )

lazy val servicesBlock = createProject(name = "ServicesBlock", baseFile = "services-block")
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.scalaParallelCollections,
      Dependencies.pureConfig,
      Dependencies.scalaLogging,
      Dependencies.sl4j,
      Dependencies.scalaTest % Test, // 'Test' means that those deps will be only used in the test scope (`/test` file path)
      Dependencies.scalaCheck % Test,
    )
  )

lazy val modules: Seq[ProjectReference] = Seq(foundationsBlock, servicesBlock, fileWriterBlock)

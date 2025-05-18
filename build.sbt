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

def createModule(moduleName: String): Project =
  Project(id = moduleName, base = file(moduleName))
    .settings(Settings.ScalaCompiler)
    .settings(Aliases.all)

lazy val towerBlocks = Project("tower-blocks", file("."))
  .aggregate(foundationsBlock, fileWriterBlock, serverBlock)

lazy val foundationsBlock = createModule("foundations-block")
  .dependsOn(serverBlock)
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

lazy val fileWriterBlock = createModule("file-writer-block")
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

lazy val serverBlock = createModule("server-block")
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

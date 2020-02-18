val dependenciesGraphVersion = "0.10.0-RC1"
val scalaFmtVersion = "2.2.1"

addSbtPlugin(
  "net.virtual-void" % "sbt-dependency-graph" % dependenciesGraphVersion
)
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % scalaFmtVersion)

package services.B1.ServerSide.config

object ServerSideConfig {

  final case class ServiceConfig(serverConfig: ServerConfig)

  final case class ServerConfig(port: Int)

}

package services.B1.ServerSide.config

import pureconfig.ConfigReader

object ServerSideConfig {

  final case class ServiceConfig(serverConfig: ServerConfig) derives ConfigReader

  final case class ServerConfig(port: Int) derives ConfigReader

}

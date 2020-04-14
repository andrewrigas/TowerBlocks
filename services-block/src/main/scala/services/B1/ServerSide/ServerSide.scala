package services.B1.ServerSide

import pureconfig.ConfigSource
import services.B1.ServerSide.config.ServerSideConfig.ServerConfig
import pureconfig.generic.auto._

object ServerSide extends App {

  //Read the configurations for the Server from application.conf in Resources
  //Learn More about pureConfig library here: https://pureconfig.github.io/
  val serverConfig = ConfigSource.default.loadOrThrow[ServerConfig]

  println("Stupid Server Created")
  val server: StupidServer = StupidServer(serverConfig.port)

  println("Start listening")
  server.listenForClients

}






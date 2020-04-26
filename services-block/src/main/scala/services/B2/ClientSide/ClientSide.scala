package services.B2.ClientSide

import pureconfig.ConfigSource
import pureconfig.generic.auto._
import services.B2.ClientSide.config.ClientSideConfig.clientServiceConfig

object ClientSide extends App {

  //Read the configurations for the Client from application.conf in Resources
  //Learn More about pureConfig library here: https://pureconfig.github.io/
  val clientConfig = ConfigSource.default.loadOrThrow[clientServiceConfig]

  val client = Client(clientConfig.clientConfig.host,clientConfig.clientConfig.port)

  client.makeRequest

}

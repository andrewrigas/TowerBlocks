package services.B2.ClientSide

import pureconfig.ConfigSource
import services.B2.ClientSide.config.ClientSideConfig.ClientConfig
import pureconfig.generic.auto._

object ClientSide extends App {

  //Read the configurations for the Client from application.conf in Resources
  //Learn More about pureConfig library here: https://pureconfig.github.io/
  val clientConfig = ConfigSource.default.loadOrThrow[ClientConfig]

  val client = Client(clientConfig.host,clientConfig.port)

  client.connectToServer

}

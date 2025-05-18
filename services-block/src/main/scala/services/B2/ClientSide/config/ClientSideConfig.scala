package services.B2.ClientSide.config

import pureconfig.ConfigReader

object ClientSideConfig {

  final case class ClientServiceConfig(clientConfig: ClientConfig) derives ConfigReader

  final case class ClientConfig(host: String, port: Int) derives ConfigReader

  object ClientConfig {
    def apply(host: String, port: Int): ClientConfig = new ClientConfig(host.toLowerCase, port)
  }

}

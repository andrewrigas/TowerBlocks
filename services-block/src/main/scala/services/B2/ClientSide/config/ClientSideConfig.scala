package services.B2.ClientSide.config

object ClientSideConfig {

  final case class clientServiceConfig(clientConfig: ClientConfig)

  final case class ClientConfig(host: String,port: Int)

  object ClientConfig {
    def apply(host: String, port: Int): ClientConfig = new ClientConfig(host.toLowerCase, port)
  }

}

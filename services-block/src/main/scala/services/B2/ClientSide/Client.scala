package services.B2.ClientSide

import java.io.PrintWriter
import java.net.Socket

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.matching.Regex

final case class Client private (host: String,port: Int) extends LazyLogging {


  def connectToServer = {
    logger.info(s"Client trying to connect to $host:$port")
    val socket: Socket = new Socket(host,port)
    logger.info(s"Successful client connection with the server $host:$port")
    Future {
      sendMessage(socket)
    }.onComplete(_ => socket.close())

  }

  def sendMessage(socket: Socket): Unit = {
    val out = new PrintWriter(socket.getOutputStream,true)
    out.println("Hello Server")
  }

}

object Client extends LazyLogging {

  private def validatePort(port: String): Int = {
    val portPattern: Regex = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$".r
    portPattern.findFirstIn(port) match {
      case Some(value) => value.toInt
      case None => {
        logger.debug(s"Invalid port: $port")
        throw new IllegalArgumentException(s"Invalid port Number: $port")
      }
    }
  }

  private def validateHost(host: String): String =
    if(host.nonEmpty) host
    else {
      logger.debug("Invalid host Name")
      throw new IllegalArgumentException(s"Invalid Host, should not be Empty")
    }

  def apply(host: String, port: String): Client = {
    val validHost = validateHost(host)
    val validPort = validatePort(port)
    new Client(validHost, validPort)
  }

  def apply(host: String, port: Int): Client = Client(host,port.toString)

}

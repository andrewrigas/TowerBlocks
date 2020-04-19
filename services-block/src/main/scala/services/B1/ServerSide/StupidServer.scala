package services.B1.ServerSide

import java.io.{BufferedReader, InputStream, InputStreamReader, OutputStream, PrintStream}
import java.net.{ServerSocket, Socket}
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.matching.Regex

final case class StupidServer private (port: Int) extends LazyLogging {

  //Configure your socket and create an Server Socket object
  val serverSocket: ServerSocket = new ServerSocket(port)

  def listen = {
    logger.info("Waiting for a client...")
    val socketAccept: Socket = serverSocket.accept() //Create a socket as soon as you get a connection
    logger.info("New connection established")
    val task: Future[Unit] = Future {
      val inputStream: InputStream = socketAccept.getInputStream
      val outputStream: OutputStream = socketAccept.getOutputStream
      service(inputStream, outputStream)
    }
    task.onComplete(_ => socketAccept.close())
  }

  def listenForClients = {
    while (true) {
      listen
    }
  }

  def service(inputStream: InputStream,outputStream: OutputStream): Unit = {
    val in = new BufferedReader(new InputStreamReader(inputStream))
    val clientMsg = in.readLine()
    println(s"Client msg: $clientMsg")

    val out = new PrintStream(outputStream)
    out.println("You msg has been received...")

  }
}

object StupidServer extends LazyLogging {

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

  def apply(port: String): StupidServer = {
    val validPort: Int = validatePort(port)
    new StupidServer(validPort)
  }

  def apply(port: Int): StupidServer = StupidServer(port.toString)
}

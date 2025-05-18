package services.B1.ServerSide

import java.io.{BufferedReader, InputStream, InputStreamReader, OutputStream, PrintStream}
import java.net.{ServerSocket, Socket}
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.matching.Regex

final case class StupidServer private (port: Int) extends LazyLogging {

  // Configure your socket and create an Server Socket object
  val serverSocket: ServerSocket = new ServerSocket(port)

  def listen = {
    logger.info("Waiting for a client...")
    // ServerSocket accept will create a socket and (block) wait until a connection with a client has been established
    val socket: Socket = serverSocket.accept() // Create a new socket as soon as you get a connection
    logger.info("New connection established")
    Future {
      // Streams read bytes
      // Input stream reads from the socket
      val inputStream: InputStream = socket.getInputStream
      // Output stream writes on the socket
      val outputStream: OutputStream = socket.getOutputStream
      service(inputStream, outputStream)
    }.onComplete(_ => socket.close())
  }

  def listenForClients =
    while (true) {
      listen
    }

  def service(inputStream: InputStream, outputStream: OutputStream): Unit = {
    // InputStreamReader transform our inputStream to Chars
    val inputStreamReader: InputStreamReader = new InputStreamReader(inputStream)
    // BufferReader read each char from inputStreamReader into a buffer
    val in: BufferedReader = new BufferedReader(inputStreamReader)
    // readLine will read all the bytes from the input stream until the end of a line
    val clientMsg: String = in.readLine()
    println("Client msg: " + clientMsg)
    val out: PrintStream = new PrintStream(outputStream)
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

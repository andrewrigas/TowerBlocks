package services.B2.ClientSide

import java.io.{BufferedReader, InputStream, InputStreamReader, OutputStream, PrintStream, PrintWriter}
import java.net.Socket

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.matching.Regex

final case class Client private (host: String,port: Int) extends LazyLogging {

  final case class IO(input: BufferedReader, output: PrintStream)

  def makeRequest = {
    while (true){
      val socket = connectToServer
      val io = getIO(socket)
      Thread.sleep(1000)
      Future{clientRequest(io,"Hello Server")}.onComplete(_ => socket.close())
    }
  }

  private def connectToServer = {
    logger.info(s"Client trying to connect to $host:$port")
    val socket: Socket = new Socket(host,port)
    logger.info(s"Successful client connection with the server $host:$port")
    socket
  }

  private def getIO(socket: Socket): IO = {
    //Hint
    //Readers read characters
    //Streams read bytes

    //Output stream writes on the socket
    val outputStream: OutputStream = socket.getOutputStream
    val myOutput: PrintStream = new PrintStream(outputStream)

    //Input stream reads from the socket
    val inputStream: InputStream = socket.getInputStream
    //InputStreamReader transform our inputStream to Chars
    val inputStreamReader: InputStreamReader = new InputStreamReader(inputStream)
    //BufferReader read each char from inputStreamReader into a buffer
    val myInput: BufferedReader = new BufferedReader(inputStreamReader)

    IO(input = myInput,output = myOutput)
  }

  private def clientRequest(io: IO, message: String): Unit = {
    //Output stream writes on the socket
    //Write on socket string message
    io.output.println(message)

    //Input stream reads from the socket
    //Read a line from the server message
    val serverResponse: String = io.input.readLine()
    println(s"Server response: $serverResponse")
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

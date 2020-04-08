package services.B1.ServerSide

import java.io.{InputStream, OutputStream}
import java.net.ServerSocket

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.util.matching.Regex

final case class StupidServer(port: Int){

  implicit val ec: ExecutionContext = ExecutionContext.global

  //Configure your socket and create an Server Socket object
  val serverSocket: ServerSocket = new ServerSocket(port)

  def listen(serverSocket: ServerSocket) = {
    val socketAccept = serverSocket.accept()
    val task = Future {
      val inputStream: InputStream = socketAccept.getInputStream
      val outputStream: OutputStream = socketAccept.getOutputStream
      service(inputStream, outputStream)
    }
    task.onComplete(_ => socketAccept.close())
  }

  def service(inputStream: InputStream,outputStream: OutputStream): Unit = ???
}

object StupidServer extends App{

  def apply(port: String): StupidServer = {
    val portPattern: Regex = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$".r
    val port = portPattern.findFirstIn(port) match {
      case Some(value) => value.toInt
      case None => 80
    }
    new StupidServer(port)
  }

  def apply(port: Int): StupidServer = {
    apply(port.toString)
  }


}

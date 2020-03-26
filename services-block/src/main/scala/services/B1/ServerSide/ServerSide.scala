package services.B1.ServerSide

import java.net.ServerSocket
import java.io.{BufferedInputStream, BufferedOutputStream, PrintStream}

object ServerSide extends App{

  val port = 8080

  val ss = new ServerSocket(port)

  val sock = ss.accept()

  val is = new BufferedInputStream(sock.getInputStream)

  val os = new PrintStream(new BufferedOutputStream(sock.getOutputStream))

  os.println("Hi there..")
  os.flush()

  while (is.available() < 1) {

    Thread.sleep(100)

    val buffer = new Array[Byte](is.available)

    is.read(buffer)

    val input = new String(buffer)

    if (input.size > 0) {
      println(input)
      os.println(input)
    }

    os.flush()
  }
}

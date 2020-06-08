package B1.FileWriter

import java.io._

import B1.FileWriter.Config.FileWriterConfig.FileWriterConfiguration
import pureconfig.ConfigSource
import pureconfig.generic.auto._

object FileWriter extends App {

  val fileWriterConfig = ConfigSource.default.loadOrThrow[FileWriterConfiguration]

  val message = "Hello World!"

  val file: File = new File(fileWriterConfig.filePath + fileWriterConfig.fileName)

  val printWriterSocket: PrintWriter = new PrintWriter(file)
  printWriterSocket.write(message)
  printWriterSocket.close

//  // FileWriter
//  val file = new File(fileWriterConfig.filePath)
//  val bufferedWriterSocket = new BufferedWriter(new FileWriter(file))
//  bufferedWriterSocket.write()
//  bufferedWriterSocket.close()
}

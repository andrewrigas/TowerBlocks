package foundations.Exercises.B7.ErrorHandling

import com.typesafe.scalalogging.LazyLogging

import scala.util.Try
import scala.util.matching.Regex

object ErrorHandling extends LazyLogging {

  def division(dividend: Double, divisor: Double): Option[Double] = {
    Try(dividend / divisor).toOption match {
      case Some(value) => Some(value)
      case None        => None
    }
  }

  sealed trait HttpServerError {
    def errorCode: String
  }

  object HttpServerError {
    case object Forbidden extends HttpServerError {
      val errorCode: String = "403"
    }

    case object NotFound extends HttpServerError {
      val errorCode: String = "404"
    }

    case object TimeOutRequest extends HttpServerError {
      val errorCode: String = "408"
    }

    case object Gone extends HttpServerError {
      val errorCode: String = "410"
    }
  }

  final case class Url private (url: String)

  case object Url {
    import foundations.Exercises.B6.MasteringImplicits.MasteringImplicits.TypeHelpers.BoolToEither
    import foundations.Solutions.B4.AlgebraicDataTypes.AlgebraicDataTypes.EitherB

    sealed trait UrlError

    case object InvalidUrlError extends UrlError

    private def checkUrl(url: String): Boolean = {

      logger.info(s"Url: info => validating $url url") //log

      val urlRegex: Regex =
        """^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$""".r
      urlRegex.findFirstIn(url) match {
        case Some(_) => true
        case None    => false
      }
    }

    def apply(url: String): EitherB[UrlError, Url] =
      checkUrl(url).fold(InvalidUrlError, new Url(url))

    def unsafeApply(url: String) = new Url(url)
  }

  trait Http {
    def host: Url
  }

  final case class HttpClient(host: Url) extends Http {

//    def makeRequest(host: String,)

  }

  object HttpClient extends Http {
    import foundations.Solutions.B4.AlgebraicDataTypes.AlgebraicDataTypes.EitherB._
    val host: Url = Url.unsafeApply("localhost") //Default Vale

    sealed trait HttpClientError

    //???

    def apply(url: Url) = new HttpClient(url)

    def apply(str: String): HttpClient = {
      val url: Url = Url(str) match {
        case RightB(value) => {
          logger.info(s"HttpClient: info => success parsing $str") //log
          value
        }
        case _ => {
          logger.warn(
            s"HttpClient: warn => url validation error $str failed to parse to Url"
          ) //log
          host
        }
      }
      new HttpClient(url)
    }

  }

}

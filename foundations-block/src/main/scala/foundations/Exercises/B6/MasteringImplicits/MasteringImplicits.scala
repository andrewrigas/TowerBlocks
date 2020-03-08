package foundations.Exercises.B6.MasteringImplicits

object MasteringImplicits extends App {

  final case class Summoner(name: String)

  implicit object Summoner{
    def apply(name: String) = new Summoner(name)
  }

  def createSummoner(implicit summ: Summoner.type) = summ("Andreas")

  object TypeHelpers {

    implicit class IntHelper(value: Int) {
      def addFour: Int = value + 4
      def factorial: Long = {
        def fac(x: Long): Long = {
          if(x == 0) 1
          else if(x > 0) x * fac(x-1)
          else x * fac(x+1)
        }
        fac(value.toLong)
      }
    }

    implicit class TypeHelp[+T](x: T) {
      def map[B](f: T => B) = f(x)
    }
  }

//  import TypeHelpers._
//
//  println(3.addFour)
//  println(5.factorial)
//  println(-4.factorial)
//
//  val hl = "Hello".map(x => x + "World")
//  println(hl)
//
//  val d = 4.map(x => x + 1)
//  println(d)

  sealed trait EventAction[A] { //TypeClasses
    def watchedEvent: String
  }

  sealed trait WatchedEvent

  final case class PlayEvent() extends WatchedEvent

  final case class StopEvent() extends WatchedEvent

  final case class ClickEvent() extends WatchedEvent

  implicit val playEventAction: EventAction[PlayEvent] =
    new EventAction[PlayEvent] {
      val watchedEvent: String = "Play Event"
    }

  implicit val stopEventAction: EventAction[StopEvent] =
    new EventAction[StopEvent] {
      val watchedEvent: String = "Stop Event"
    }

  implicit val clickEventAction: EventAction[ClickEvent] =
    new EventAction[ClickEvent] {
      val watchedEvent: String = "Click Event"
    }

  def giveMeEvent[A](implicit singleEvent: EventAction[A]): EventAction[A] =
    singleEvent

  val eventMsgPlayEvent = giveMeEvent[PlayEvent]

  val eventMsgStopEvent = giveMeEvent[StopEvent].watchedEvent

//  println(eventMsgPlayEvent.watchedEvent)
//  println(eventMsgStopEvent)

  implicit def pairEvents[HEAD, TAIL](
    implicit
    eventsHead: EventAction[HEAD],
    eventsTail: EventAction[TAIL]
  ): EventAction[(HEAD, TAIL)] = new EventAction[(HEAD, TAIL)] {
    val watchedEvent: String = {
      s"(${eventsHead.watchedEvent} -> ${eventsTail.watchedEvent})"
    }
  }

  type playStopEvents =
    EventAction[
      (PlayEvent,
       (ClickEvent, (StopEvent, (PlayEvent, (ClickEvent, StopEvent)))))
    ]

  val pairEventsResolution = implicitly[playStopEvents].watchedEvent

//  println(pairEventsResolution)

}

package foundations

object MasteringImplicits {

  sealed trait Events[+A] {
    //Almost in all cases we declare def instead of val in our TypeClasses
    def value: A
  }

  final case class PlayLive[A](value: A) extends Events[A] {
    override def toString: String = "Play Live Event"
  }

  final case class StopLive[A](value: A) extends Events[A] {
    override def toString: String = "Stop Live Event"
  }

  final case class PlayVod[A](value: A) extends Events[A] {
    override def toString: String = "Play Vod Event"
  }

  final case class StopVod[A](value: A) extends Events[A] {
    override def toString: String = "Stop Vod Event"
  }

}

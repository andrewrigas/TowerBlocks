package foundations

object MasteringImplicits {

  sealed trait Events[+A] {
    //Almost in all cases we declare def instead of val in our TypeClasses
    def watchedEvent: String
  }

}

package foundations

object MasteringImplicits {

  implicit val ImplicitInt = 10

  def funcConcatStringInt(str: String)(implicit value: Int): String = {
    str + value
  }

}

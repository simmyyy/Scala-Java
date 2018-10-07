package object scalaCore {

  implicit class ImplicitsFun[A](xs: List[A]) {
    def findObject[A](value: A): Maybe[Int] = {
      val index = xs.indexOf(value)
      if (index != -1) Just(index) else Nil
    }

    def findSomeObject[A](value: A): Some[Int] = {
      val index = xs.indexOf(value)
      Some(index)
    }
  }

}

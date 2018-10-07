package scalaCore

object OptionalLikeFeature {

  def main(args: Array[String]): Unit = {

    val sampleList = List(1, 2, 3, 4, 5, 6)
    println(findObject(sampleList, 5))
    println(findObject(sampleList, 50))

    //or extend library and use implicit function
    println(sampleList findObject 5)
    println(sampleList findObject 50)

    println(sampleList findSomeObject 5)
    println(sampleList findSomeObject 50)


  }


  def findObject[A](xs: List[A], value: A): Maybe[Int] = {
    val index = xs.indexOf(value)
    if (index != -1) Just(index) else Nil
  }


}

class Positionable[A](xs: List[A]) {

  def position[A](value: A): Maybe[Int] = {
    val index = xs.indexOf(value)
    if (index != -1) Just(index) else Nil
  }

}

sealed abstract class Maybe[+A] {
  def isEmpty: Boolean

  def get: A
}

final case class Just[A](value: A) extends Maybe[A] {
  override def isEmpty: Boolean = false

  override def get: A = value
}

case object Nil extends Maybe[Nothing] {
  override def isEmpty: Boolean = true

  override def get: Nothing = throw new NoSuchElementException("nil.get")
}


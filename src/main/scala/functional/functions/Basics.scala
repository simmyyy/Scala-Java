package functional.functions

object Basics {

  val succFunctionOut = new Function1[Int, Int] {
    def apply(x: Int): Int = x + 1
  }
  val succOut = (x: Int) => x + 1

  def main(args: Array[String]): Unit = {

    /**
      * This is how you can declare functions. Functions are:
      *   - actual implementation of
      *
      * succFunctionIn and succIn are actually the same function
      * succIn
      */
    val succFunctionIn = new Function1[Int, Int] {
      def apply(x: Int): Int = x + 1
    }
    val succIn = (x: Int) => x + 1



  }


}

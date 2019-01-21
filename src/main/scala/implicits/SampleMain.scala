package implicits


object SampleMain {

  implicit class longToGreetingsConverted(ln: Long) {
    def greet(): String = "Hello, Long!"
  }

  def runFunctionImplicitExamples(): Unit = {

    implicit class doubleToGreetingsConverted(double: Double) {
      def greet(): String = "Hello, Double!"
    }

    println(5.greet())
    println(5L.greet())
    println(5D.greet())
  }

  def main(args: Array[String]): Unit = {
    runFunctionImplicitExamples()
  }


}

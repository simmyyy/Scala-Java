package object implicits {

  implicit class intToGreetingsConverted(int: Int) {
    def greet(): String = "Hello, int!"
  }

}

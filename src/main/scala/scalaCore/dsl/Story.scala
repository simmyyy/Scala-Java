package scalaCore.dsl

/**
  * DSL (Domain specific language) example
  *
  * This has been found in one of Github repositories but added some
  * more explanation about each step
  *
  */
object Story {

  object Once {
    def upon: a.type => a.type = (x: a.type) => {
      println(a.callMe)
      a.callMe = "UpOn2!"
      x
    }
  }

  object a {

    var callMe = "UpOn!"

    def time: languages.type => languages.type = (x: languages.type) => x
  }

  object languages {
    def were: inflexible.type => inflexible.type = (x: inflexible.type) => x
  }

  object inflexible {
    def along: came.type => came.type = (x: came.type) => x
  }

  object came {
    def Scala: inflexible.type => inflexible.type = (x: inflexible.type) => x
  }

  implicit def string(s: String): inflexible.type = {
    println("Implicit conversion!")
    inflexible
  }

  def main(args: Array[String]): Unit = {
    Once upon a time languages were inflexible
    "Then " along came Scala ". The End!"

    Once.upon(a).time(languages).were(inflexible)
    "Then ".along(came).Scala(". The End!")

    /**
      * What is happening here step by step:
      *
      *   1. Once has method "upon" which takes A object as parameter
      *   2. When method "upon" is called it prints callMe value of A object
      *   3. Then it changes callMe to another value and return the same object
      *   4. Once.upon(a) returns just a which has method "time"
      *   5. The same happens for next few classes
      *   6. When "'Then' along" is called compiler checks that String does not have method "along"
      *   7. Compiler checks that implicit method exists which can convert String to inflexible which has method along
      *   8. Method along is called and this happens the same for class "came"
      *   9. Implicit conversion also happens at the end of the method execution
      *   10. Function "Scala" needs inflexible so compiler does the same thing as in point 7 and 8
      *
      * Similar steps also happens for 2nd execution (those with dots) but this time "UpOn2!" is called as
      * it was changed during the first execution
      */
  }
}

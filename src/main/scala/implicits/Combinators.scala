package implicits

object Combinators {

  implicit def kestrel[A](a: A) = new {
    def tap(sideEffect: A => Unit): A = {
      sideEffect(a)
      a
    }

    def sink(anotherSideEffect: A => Unit): A = {
      println("Hello external device!")
      a
    }
  }

  case class Person(firstName: String, lastName: String)

  case class Mailer(emailAddress: String) {
    def mail(body: String) = {
      println("send email here...")
    }
  }

}

object Main {
  def main(args: Array[String]): Unit = {
    import Combinators._

    /**
      * How this implicit works?
      *
      * 1. Create Person - obvious
      * 2. invoke tap on Person object - failed to compile
      * 3. Compiler looks for any implicit object which could translate Person to "Something" that can call tap
      * 4. Compiler finds implicit method kestrel that takes any type parameter (so Person applies)
      *    and provides implementation of method tap
      * 5. Compiles converts object Person using kestrel method to "something" that implements tap then
      * 6. Additionally, tap requires parameter which is actually a function "sideEffect: A => Unit"
      * 7. As return type of tap is A (here - Person) we can chain that with sink method which works very similar to tap
      * 8. at the end get lastname and print it as well
      *
      * Simple, is'n it? ;p
      *
      */
    Person("asd", "ASD").tap(p => {
      println("Firstname: " + p.firstName)
      Mailer("someAddress")
    }).sink(p => {
      println("Does it really matter?")
    }).lastName
  }
}

package functional

import functional.Combinators.{Mailer, Person}

object Combinators {

  implicit def kestrel[A](a: A) = new {
    def tap(sideEffect: A => Unit): A = {
      sideEffect(a)
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
    Person("asd", "ASD").tap(p => {
      println("Firstname: " + p.firstName)
      Mailer("someAddress")
    }).lastName
  }
}

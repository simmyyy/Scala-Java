package types.variances

class Foo[+A] // A covariant class
class Bar[-A] // A contravariant class
class Baz[A] // An invariant class

abstract class Animal {
  def name: String
}

/**
  * Those 2 classes are subclasses of Animal
  */
case class Cat(name: String) extends Animal

case class Dog(name: String) extends Animal


abstract class Printer[-A] {
  def print(value: A): Unit
}


class AnimalPrinter extends Printer[Animal] {
  def print(animal: Animal): Unit =
    println("The animal's name is: " + animal.name)
}

class CatPrinter extends Printer[Cat] {
  def print(cat: Cat): Unit =
    println("The cat's name is: " + cat.name)
}


/**
  *
  * START
  *
  *
  * Those examples are based on scala-lang.org docs with maybe a little simpler explanation/tests
  *
  * @param args
  */
object SampleVariances {

  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      println(animal.name)
    }
  }

  def main(args: Array[String]): Unit = {

    /**
      * Testing Covariance
      *
      * NOTE!!List is a covariant type as per documentation:
      * type List[+A] = scala.collection.immutable.List[A]
      *
      * It means, if we define that method parameter takes List[Animal]
      * so you can pass all Animal subclasses to that method e.g.
      * List[Cat]
      *
      */
    def printAnimalNames(animals: List[Animal]): Unit = {
      animals.foreach { animal =>
        println(animal.name)
      }
    }

    val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
    val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))
    printAnimalNames(cats)
    // Whiskers
    // Tom
    printAnimalNames(dogs)
    // Fido
    // Rex


    /**
      * Testing Contravariance
      *
      * Printer is defined as
      * abstract class Printer[-A]
      * It means method can take [Cat] and all is superclasses e.g. Animal
      *
      */
    val myCat: Cat = Cat("Boots")

    def printMyCat(printer: Printer[Cat]): Unit = {
      printer.print(myCat)
    }

    val catPrinter: Printer[Cat] = new CatPrinter
    val animalPrinter: Printer[Animal] = new AnimalPrinter
    printMyCat(catPrinter) // The cat's name is: Boots
    printMyCat(animalPrinter) // The animal's name is: Boots

    /**
      * Invariance is actually neither Covariance nor Contravariance means you have to pass
      * exact time as is defined e.g. in method.
      */


  }

}

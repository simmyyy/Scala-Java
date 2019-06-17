package basic.definingthings

/**
  * This is object BasicDefititions along with class BasicDefinitions
  *
  * Object is also called "companion object" of BasicDefinitions class
  */
object BasicDefinitions {
  def main(args: Array[String]): Unit = {
    new BasicDefinitions().assignmentExamples()
  }
}


class BasicDefinitions {

  val fixedValue = 1

  def assignmentExamples(): Unit = {

    /**
      * There are 4 simple ways to define things in Scala:
      * * def - defines a method
      * * val - defines a fixed value
      * * var - defines a variable
      * * lazy val - similar to "val" but is executed when first time accessed
      */

    /**
      * Note this is a METHOD! not just instatiation of BasicDefinitions class
      * @return
      */
    def person = new BasicDefinitions()

    println(person) // basic.definingthings.BasicDefinitions@78b1cc93

    def person2(i: Int) = {new BasicDefinitions(); println(i)}

    person2(5) // 5

    val inst = new BasicDefinitions
    println(inst.fixedValue) // 1

    /**
      * Below does not compile because of inst cannot be reassigned
      */
    // inst = new BasicDefinitions

    /**
      * var can be re-assigned
      */
    var inst2 = new BasicDefinitions
    inst2 = new BasicDefinitions

  }

}

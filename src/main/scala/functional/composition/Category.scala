package functional.composition

class Category {

  /**
    * Please note that "idWhat" is actually equivalent to method "id". It may be a little bit
    * tricky syntax for Java Developers (at least for me it was), here it means basically:
    * """
    * I hava a type "What" and I have a function called "idWhat" which returns
    * Function mapping what=>what and now my implementetaion is
    * function literal whatever => whatever
    * """
    *
    * Method id is the same
    *
    * @tparam what
    * @return
    */
  def idWhat[what]: what => what = whatever => whatever

  /**
    * This example is more Java-like in my opinion. It now takes 2 parameters and converts it to String
    *
    * @tparam what
    * @tparam what2
    * @return
    */
  def idWhatsToString[what, what2]: (what, what2) => String = (whatever, whatever2) => whatever2 + whatever.toString

  def id[A]: A => A = {
    a => a
  }

  /**
    * Syntax may also be a little bit tricky so let explain:
    * A,B,C -> type parameters
    * (g and f) are actually normal method parameters with their types
    * ": A => C" is that return type is actually a function literal
    * "= g compose f" is means:
    * run my function f first which converts A to B
    * as return type of f is B so apply method g
    * on the result to convert B to C
    * that is why return of whole function is actually A => C
    *
    * @param g
    * @param f
    * @tparam A
    * @tparam B
    * @tparam C
    * @return
    */
  def compose[A, B, C](g: B => C, f: A => B): A => C =
    g compose f

}

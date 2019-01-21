package functional.composition

trait GenericCategory[->>[_,_]] {
  def id[A]: A ->> A
  def compose[A, B, C](g: B ->> C, f: A ->> B): A ->> C
}

package foundations.Experiments

object CaseClassExp extends App {

  // What Case Keyword add to your class
  // Every object that you create is immutable
  // That's why new keyword is no longer needed
  class Person(val name: String, val age: Int) {

    def apply(name: String, age: Int): Person =
      new Person(name, age)

    def unapply: Option[(String, Int)] = Some((this.name, this.age))

    def copy(name: String = this.name, age: Int = this.age): Person =
      new Person(name, age)

    def equals(person: Person): Boolean =
      this.name == person.name && this.age == person.age

    // Used to identify if the instance of tow objects are the same
    // Each Instance has different hashcode
    override def hashCode(): Int = name.hashCode * 3 + age.hashCode() * 5

    override def toString: String = s"Person(${this.name},${this.age})"
  }

}

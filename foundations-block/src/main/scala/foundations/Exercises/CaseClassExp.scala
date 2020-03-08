package foundations.Exercises

object CaseClassExp extends {

  class Person(val name: String,val age: Int) {

    def apply(name: String,age: Int): Person = {
      new Person(name,age)
    }

    def unapply: Option[(String,Int)] = Some((this.name, this.age))

    def copy(name: String = this.name, age: Int = this.age): Person = {
      new Person(name,age)
    }

    def equals(person: Person): Boolean = {
      this.name == person.name && this.age == person.age
    }

    override def hashCode(): Int = name.hashCode * 3 + age.hashCode() * 5

    override def toString: String = s"Person(${this.name},${this.age})"
  }

}

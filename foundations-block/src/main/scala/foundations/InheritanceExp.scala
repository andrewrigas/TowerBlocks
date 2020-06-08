package foundations

object InheritanceExp extends App {

  //Super class or Parent class
  abstract class Human

  //Traits similar to Interfaces in java
  trait HumanBehaviours

  //Sub classes or Child class
  class Woman extends Human with HumanBehaviours
  //You cannot extend more than one abstract class on a subclass
  //The order matters extends Abstract Class with Only Traits are allowed
  //class Man extends HumanBehaviours with Human //Illegal

  //Experiment 1
  abstract class Animal

  trait AnimalBehaviours extends Animal
  trait MotorsBehaviour

  class RobotTiger extends AnimalBehaviours with MotorsBehaviour
  //I extended an abstract class on the trait which that means the trait behaves as an abstract class
  //Intellij cant see it compiler can find it
  //class RobotLion extends MotorsBehaviour with AnimalBehaviours

  //Experiment 2
  abstract class Person

  abstract class Adult extends Person

  abstract class Student extends Adult

  //I can extend only one abstract class but this abstract class
  // can have multiple inheritance from other abstract classes
  class ComputerScienceStudent extends Student


}

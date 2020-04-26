package foundations
import scala.collection.parallel.CollectionConverters._

object RaceConditionExp extends App {

  //Mutable variables
  var pi: Int = 0
  var i: Int = 0

  //Sequential
  for(j <- 1.to(10000)){
    i += 1
  }

  //Parallel collections in scala .par
  //Parallel
  for(j <- (1 to 10000).par){
    //i is pointing at an Address in Ram memory
    //The command below will do 3 actions
    //Load the Address bytes from Ram memory to a CPU register
    //Add 1 to the bytes
    //Store back to the same Address the new value in Ram memory
    //Interruptions in parallelism might occur at any stage of those 3 Actions
    pi += 1
  }

  println(i)
  println(pi)
}

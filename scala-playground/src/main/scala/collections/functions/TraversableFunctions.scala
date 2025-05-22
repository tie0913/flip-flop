package collections.functions

/**
  * This class is a demostration of methods of Traversable trait, which is the top trait of all collections
  * including List Array StringBuffer Set Queue Deque and so forth
  * 
  * We need to remember Map is not an instance of Traversable in Scala 2.13 and plus
  * But before Scala 2.13 Map is an instance of Traversable
  */
object TraversableFunctions extends App{

    val data = List(1,2,3,4,5,6,7,8,9)

    /**
      * 
      */  
    val collectToString = data.collect[Int]{case x if x%2 == 0 => x}

    println(data)

    println(collectToString)

}

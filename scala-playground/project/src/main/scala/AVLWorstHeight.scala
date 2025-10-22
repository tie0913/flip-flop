import scala.collection.immutable.Queue
object AVLWorstHeight extends App{
  

    var n_1 = 2
    var n_2 = 1
    var height = 2
    while(n_1 <= 1000000000){
        height = height + 1
        val temp = n_1
        n_1 = n_1 + n_2 + 1
        n_2 = temp

        println(s"F(${height}) = ${n_1}")
    }



}

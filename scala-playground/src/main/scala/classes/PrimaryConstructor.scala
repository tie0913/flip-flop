package classes

/**
  * @param name
  * @param age
  * @param gender
  * 
  * 1. the primary constructor of a class is its whole body. 
  *    The parameters within parentheses are the arguments of the primary constructor
  *    The scope of primar consctrutor is the whole class body.
  * 
  * 2. private val - access inside, cannot be changed
  *    val           access anywhere by default getter, cannot be changed
  *    var           access anywhere by default getter, can be changed by default getter
  *    private var - access inside, can be changed inside.
  * 
  * 3. what are default getter and setter.
  *    when we define a val field in class, scala provides its getter method but we can not see it because of syntatic sugar.
  *    when we define a var field in class, scala provides its setter method as well, and we can not see it.
  * 
  *    but we can invoke them by the name of that field
  * 
  */
class PrimaryConstructor (private val name:String, val age:Int, var gender:String, private var salary:Double){
  
}
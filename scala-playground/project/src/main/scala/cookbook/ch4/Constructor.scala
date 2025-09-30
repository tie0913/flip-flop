

class Person(var firstName: String, var lastName:String){

    println("The constructor begins")

    private val HOME = System.getProperty("user.home")

    var age = 0

    override def toString() = s"$firstName $lastName is $age years old"

    def printHome {println(s"HOME = $HOME")}
    def printFullName {println(this)}

    printHome
    printFullName
    println("still in the constructor")
}

/**
  * for var scala provides getter and setter
  * for val scala provides getter only
  * for variables without var nor val scala does not provide any getter and setter
  * for variable private val no getter so we can only read the value of that variable witnin the class
  * for varuable private var no getter nor setter provided so we can only read or write the value of that variable within the class
  * 
  */
class Scope(val name:String, var age:Int, SIN:String, private val hobby:String, private var book:String){

}

object Constructor extends App{
    val scope = new Scope("Tie", 40, "123456789", "hiking", "ScalaCookbook 1st Edition")

    println(scope.name)
    scope.age = 43
    println(scope.age)

    val p = Pet("Alaska")
    val p2 = Pet()
    println(p2)

    Cage.getInstance
}
/**
  * in scala case class is used to represent data class or java bean
  * we do not provide a lot of methods such as set/get, equals/hashcode, clone 
  * instead scala generates those methods for us
  * 
  * but if we do not provide var or val in front of the variable
  * it is a val variable as default.
  *
  * @param name
  */
case class Pet(name:String)



/**
  * Auxiliary Constructors
  * we can define auxiliary constructos with a class, but be careful, 
  * the auxiliary constructors will not generate mutator and accessor methods for their parameters
  * 
  * paramters of auxiliary constructors can not be used with the class, 
  * their scope of usage is only with that constructor
  * 
  * 1. Auxiliary Constructors are defined by creating methods named this
  * 2. Each auxiliary constructor must begin with a call to a previously defined constructor
  * 3. Each constructor must have a different signature
  * 4. One constructor calls another constructor with the name this.
  */

  class Cat(val name:String, val age:Int){


    def this(name:String){
        this(name, 1)
    }
    /**
      * this constructor invokes the auxiliary constructor with a name parameter 
      * so , it has to be defined after that auxiliary constructor.
      *
      * @return
      */
    def this(){
        this("Marco")
    }
  }


/**
  * if you want to create auxiliary constructor for a case class
  * you need to use companion object and provide apply method within it.
  * 
  * Take a look at the code below
  * 
  * also you need to know when we create a Pet in here we need to use the keyword "new"
  * and when you invoke this auxiliary constructor creating a new Pet you do not need to use the keyword "new"
  */

object Pet {
    def apply() = new Pet("No Name")
}

/**
  * how do we define a private primary constructor
  * while will help us to build singleton class in scala
  * 
  * the keyword private between the class name and the parenthese is the answer.
  * 
  * the parameters of a constructor can have defualt values
  * which means we do not need to provide multiple auxiliary constructors
  */
  class Cage private(size:Int = 1000){

      println(s"Now we are going to create a Cage with size $size")
  }

  object Cage{
    private val inst = new Cage()
    def getInstance = inst
  }

/**
  * scala would not generate setter/getter for private fields
  * 
  * private fields can be accessed by instances of current class
  * private[this] fields can only be accessed by current instance
  */


/**
  * About null in scala
  * Scala recommends us to use Optional[Type], when we define a variable which may be null
  * so we do not need to have a lot of == null everywhere.
  * 
  * we can use isDefined to verify whether it exists
  * so in Scala nothing is null. if you want to express nothing ,you should use None.
  */


/**
  * This is a super class
  * Scala will generate a getter method for name
  */
class Creature(val name:String){

  def this(name:String, gender:String){
    this(name)
  }
}

/**
  * This is a sub class of Creature
  * no modifier before the name parameter will lead Scala does not genereate the getter method
  * because we can use that method coming from the super class.
  * @param name
  * @param age
  */
class Man(name:String, var age:Int) extends Creature(name){

}

/**
  * the subclass can invoke any constructors of their superclass in their primary constructor
  * but they can not invoke any constructors of their superclass in their auxiliary constructor
  *
  * @param name
  * @param hobbies
  */
class Woman(name:String, val hobbies:Array[String]) extends Creature(name, "female"){

}


/**
  * This is how we define abstract class
  * 
  * In scala , you may need abstract class
  * when you want to
  *   1. define behaviors with public initiated fields
  *   2. invoke your code in Java (Java can not invoke trait)
  *
  * @param name
  */
abstract class Base(val name: String){
  protected def work():Unit

  private def start():Unit = {
    println(s"$name is started")
  }

  private def stop():Unit = {
    println(s"$name is stopped")
  }

  def execute():Unit = {
    this.start()
    this.work()
    this.stop()
  }
}

class Driver(name:String = "Alf") extends Base(name){
  protected override def work():Unit = {
    println(s"$name is woking")
  }
}


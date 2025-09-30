package cookbook.ch5

class Methods {
  
  /**
    * method scope option
    * 
    * 1. object-private
    * 2. private
    * 3. protected -> in java a protected method can be invoked by subclass or another class which nests in the same package with current class
    *                 but in scala, the rule is more strict, only the subclass can visit the protected method.
    * 3. package : private[package name] this will only allow other classes in the package to visit current method.
    * 4. package-specific
    * 5. public 
    */
}

/**
  * calling super method
  * The Qiwawa can invoke bark method in super class which is Dog class
  * The Dan can invoke bark methods in both super class because we use extends and with to mix all of them together.
  * However, in Qiwawa, we can not invoke bark method in Animal class by ' super[Animal].bark() '
  * You can only invoke methods coming from direct parent class
  */

  trait Animal{
    def bark():Unit = {
        println("Animal is barking")
    }
  }

  trait Dog extends Animal{
    override def bark(): Unit ={
        println("Dog is barking")
    }
  }

  class Qiwawa extends Dog{
    override def bark:Unit = {
        println("Qiwawa is barking")
        super.bark()
    }
  }

  class Dan extends Animal with Dog{
    override def bark:Unit = {
        println("Whof, whof")
        super[Dog].bark()
        super[Animal].bark()
    }
  }

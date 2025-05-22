package classes

object MainOfPrimaryConstructor extends App{
    val p = new PrimaryConstructor("Master Roshin", 95, "male", 500000.0)

    /**
      * because name is a private final field
      * we can not access and mutate it out of the class
      * 
      * println(p.name)
      * p.name = "Gouku"
      */ 

    /**
      * do remember you are not directly accessing the field age
      * you access it by default getter and setter provided by scala syntactic sugar.
      * you cannot see them but they exist
      * if you compile the class and decompile it to Java code, you will find those methods like 
      *     age_$eq() for setter
      *     age() for getter
      * 
      * 
      * p.gender = "female"
      * println(p.gender)
      */

    /**
      * you may access p.age but you can not change its value 
      * println(p.age)
      * p.age = 105
    */

}

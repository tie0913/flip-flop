package classes.primaryconstructor

import classes.VisibilityofFieldsinConstructor.FieldsVisibility

object MainOfPrimaryConstructor extends App{
    val p = new PrimaryConstructor("Master Roshin", 95, "male", 500000.0)

    /**
      * because name is a private final field
      * we can not access and mutate it out of the class
      */
      //println(p.name)    we will have error in this line
      //p.name = "Gouku"   we will have error in this line

    /**
      * do remember you are not directly accessing the field age
      * you access it by default getter and setter provided by scala syntactic sugar.
      * you cannot see them but they exist
      * if you compile the class and decompile it to Java code, you will find those methods like 
      *     age_$eq() for setter
      *     age() for getter
      * 
      */
      p.gender = "female"
      println(p.gender)
      

    /**
      * you may access p.age but you can not change its value 
      */
      println(p.age)
      //p.age = 105 we will have error in this line



      /**
        * In FieldVisibility Class we have 4 parameters in constructor
        * 
        * private val -> final field without setter and getter
        * val         -> final field only getter provided
        * var         -> field with setter and getter
        * none        -> final field without setter and getter -> the same with private val
        */

        val fieldsWithVisibility = new FieldsVisibility("a", "b", "c", "d")

        // println(fieldsWithVisibility.a) this has error because a is a prvaite field
        println(fieldsWithVisibility.b)
        ///fieldsWithVisibility.b = "B" this has error because b is modified by val
        println(fieldsWithVisibility.c)
        // println(fieldsWithVisibility.d) this is default, but scala is conservtive. it consider this as private val.

}

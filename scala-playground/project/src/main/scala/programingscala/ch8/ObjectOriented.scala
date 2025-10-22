package cookbook.programingscala.ch8

class ObjectOriented {
  
  /**
    * 定义不可继承的类用final
    * 定义类时，使用abstract可以防止使用此类的人直接创建此类的对象
    * 
    * scala中用instance来统一指代 class的实例和object对应的唯一对象
    * 
    * this 这个关键字在Scala中很少出现，因为之前那些Java中出现次数太多的啰嗦的代码 setter/getter constructor
    * 都被替换掉了，声明类的时候，类名后面的括号里写的参数列表就是构造器参数，编译器会根据参数是否有var/val来同时提供setter/getter，或单独的getter
    * 但对于不同的类，如果构造参数没写val和var，默认是val。
    * 
    */
}

/**
  * scala中这个类型定义的类叫 Value Class
  * 主要作用为方便开发人员调用一个方法时能精准快速识别到方法
  * 举例
  *     有一个方法叫 add (a:Int, b:Int, c:Int)
  *     还有一个方法 add (a:Int, b:Int)
  * 如果在Java里，调用这两个方法时就要非常小心，因为搞不好就会把参数传错。
  * 所以Scala里就弄了个Value Class，把一个基础类型的变量封装起来，调用的时候能区别出参数的数据类型和作用
  * 但到运行时，Scala编译器会把Value Class的对象处理成它所封装的基础类型的变量
  * 
  * 限制
  *   1. 如果定义了ValueClass，但在使用时，对方规定要的时ValueClass的父类型或接口类型，此时会失效
  *   2. 泛型集合也会导致失效
  *
  * @param a
  */
class ValueClass(val a: Int) extends AnyVal {
}






/**
  * 过去在Java中，经常出现父类使用类似于模板方法的时候抽象出了多个成员和方法
  * 但是却发现某个成员理应在父类中，但是却因为子类不同需要不同的数据类型和实现方式
  * 导致这个成员只能拆分到各个子类中
  * 
  * 但是Scala中提供了元类型 Type 解决了这种问题
  */

  abstract class BulkReader{
    type In
    val source : In
    def read : String
  }

  class StringBulkReader(val source: String) extends  BulkReader{
    type In = String
    def read:String = source
  }

  class FileBulkReader(val source: java.io.File) extends  BulkReader{
    type In = java.io.File
    def read:String = ""
    
  }
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

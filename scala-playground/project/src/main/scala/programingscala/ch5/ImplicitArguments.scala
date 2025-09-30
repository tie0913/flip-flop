package programmingscala.ch5

/**
 *  这部分的例子是，用Implicit Argument来实现默认参数
 *  同时 ImplicitArguments object中的第一个config，是我们自己生成的implicit argument，用来覆盖默认参数
 *
 *  参考场景：Kafka 生产者的配置，在默认情况下，重试，同一发送窗口的并行批次，以及超时都可以使用默认值
 *  但是如果发送binlog数据，我们肯定希望统一发送窗口的并行批次被设置为1，避免binlog的乱序
 */
case class ConnectionConfig(retries:Int = 3, flightsInBatch:Int = 5, timeout:Int = 3000)

class Connection(addr:String, port:Int, config:ConnectionConfig){
	println(s"We are going to connect $addr on $port with configuration $config")
}

object ConnectionFactory{
	def connect(addr:String, port:Int)(implicit config:ConnectionConfig):Connection = new Connection(addr, port, config)
	implicit val default = ConnectionConfig()
}

object ImplicitArguments extends App {
	implicit val config = ConnectionConfig(flightsInBatch = 1)
	ConnectionFactory.connect("localhost", 6379)

	import implicits._
	val row = new JRow(Map("one"-> 1, "two" -> 2.0, "three" -> "three"))
	println(row.get[Int]("one"))
}

/**
 * 这部分的代码用implicit做了两部分工作
 *
 * 1. SRow这个类是在扩展JRow类，即不修改JRow代码的情况下给JRow加入了新的功能 ( Scala 中的黑话叫： 隐士转换 Implicit Conversion)
 * 	  注意：隐式转换类，必须被定义在某个object内部，此规则用于限制隐式转换类的作用范围，预计是防止不小心让隐式转换类污染全局，导致莫名其妙的bug
 * 2. 通过implicit argument 的方式，集合implicit val 定义隐式函数变量，让编译器可以根据返回值（调用时的泛型类型）决定调用具体的那个函数变量
 *    从而达到在使用方节省代码行数的目的
 */
case class InvalidColumnName(name:String) extends RuntimeException(s"Invalid column name : $name")

class JRow(data:Map[String, Any]) {
	def getInt(key:String):Int = data.getOrElse(key, throw InvalidColumnName(key)).asInstanceOf[Int]
	def getDouble(key:String):Double = data.getOrElse(key, throw InvalidColumnName(key)).asInstanceOf[Double]
	def getText(key:String):String = data.getOrElse(key, throw InvalidColumnName(key)).asInstanceOf[String]
}

object implicits {
	implicit class SRow(jRow: JRow) {
		def get[T](key:String)(implicit toT:(JRow, String) => T):T = toT(jRow, key)
	}

	implicit val toInt: (JRow, String) => Int = (row, key) => row.getInt(key)
	implicit val toDouble: (JRow, String) => Double = (row, key) => row.getDouble(key)
	implicit val toText: (JRow, String) => String = (row, key) => row.getText(key)
}

/**
 * 在Java中，如果使用让一个集合类型作为参数并使用泛型
 * 那么，当多个函数的名字一致时，即使集合的泛型不同，由于Java编译器在编译时做了泛型擦除，这些函数会被因为是重复的函数定义，导致编译器报错
 *
 * 而在Scala中，可以通过implicit argument 结合其他参数列表中集合参数的实际调用类型，完成函数的重载，使得编译成
 * 
 * 2025-09-10 更深一步的理解
 * 这两个方法在Java编译器层面来看，如果没有隐式转换参数，他们就是一样的。因为编译过程中会做泛型擦除
 * 而利用隐式转换参数，导致在Java的编译结果看来，两个方法是不一样的，因为第二个参数不同。 
 * 与此同时，Scala编译器时没有泛型擦除了，它知道具体调用的时候，参数是带有那里泛型的Seq，所以编译能通过。
 * 
 *
 */

object MethodHasSeqWithDifferentGenerics{

	implicit object IntValues
	implicit object StringValues


	def method(param:Seq[Int])(implicit v:IntValues.type): Unit = {
		println(s"This is method with Seq[Int] : $param")
	}

	def method(param:Seq[String])(implicit v:StringValues.type) : Unit = {
		println(s"This is method with Seq[String] : $param")
	}

}
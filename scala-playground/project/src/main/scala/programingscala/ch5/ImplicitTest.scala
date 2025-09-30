package programmingscala.ch5

case class Person(name:String, age:Int)

class PersonOrder extends Ordering[Person]{
	override def compare(x: Person, y: Person): Int = x.age - y.age
}


/**
 *
 * implicit arguments
 *
 * 1. 在定义方法时，方法的最后一个参数列表可以被定义为隐式转换参数列表，此时参数列表中的每个参数必须在当前方法编译的上下文中存在一个隐士转换参数对象存在，否则编译不通过
 * 2. 定义方法时，不允许隐式转换参数与普通参数混用，所以当部分参数使用隐式转换时，使用柯里化定义的方式把隐式转换的参数列表放在最后一组参数列表总
 * 3. 当调用方法时，可以在代码上下文中不声明隐式转换参数对象，而直接创建一个普通对象传递给方法。
 * 4. 声明隐式转换参数方法
 *    4.1 在方法参数列表的第一位写implicit
 *    4.2 在方法的泛型上定义[参数类型:隐式转换类型]  -> 此步要求当前方法编译时上下文中必须有隐士转换类型对象存在
 *        然后在参数列表上使用  implicitly(隐士转换类型[参数类型]) -> 此步要求编译器寻找上下文中的隐士转换类型对象作为默认参数
 * 5. 如果有一个对象要声明为隐士转换类型对象需要。implicit val 变量名:隐士转换类型  =   创建对象语句
 *
 * @param list
 * @tparam A
 */
class ImplicitTest[A](list:List[A]) {
	def sort[B:Ordering](f:A=>B):List[A] = {
		list.sortBy(f)(implicitly[Ordering[B]])
	}

	def sort2[B](f:A=>B)(implicit ob:Ordering[B]):List[A] = {
		list.sortBy(f)
	}

	def sort3[Arr](a:Arr)(implicit ev: Arr <:< Seq[Any]): Seq[Any] = {
		a.sortWith((l, r) => l.toString.compare(r.toString) < 0)
	}
}


object Test extends App{
	implicit val personOrder:Ordering[Person] = new PersonOrder
	val i: ImplicitTest[(String, Int)] = new ImplicitTest[(String, Int)](List(("zhangsan", 15), ("lisi", 20)))
	val f:((String, Int))=>Person = (t) => new Person(t._1, t._2)
	i.sort(f)


	var n = 0
	def a = {
		n = n + 1
		n.toString
	}

	println(AA.test(a))
}

object AA {
	def test(a: => String) : String  = {
		a
		a
		s"hello $a"
	}
}

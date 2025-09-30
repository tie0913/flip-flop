package programmingscala.ch6

import scala.util.Random

case class Obj(var name:String)

object FunctionalPractice {

	val getNum:(Int)=>Int = (base:Int) => {
		println(s"Here we get the value of base $base")
		val r = Random.nextInt()
		base * r
	}

	def getNumMethod(base:Int):Int = {
		getNum(base)
	}

	def prepend(newObj:Obj, collection:List[Obj]):List[Obj] = newObj :: collection
}


object FunctionTest extends App{

	println(FunctionalPractice.getNum(3))
	println(FunctionalPractice.getNum(3))
	println(FunctionalPractice.getNumMethod(3))


	/**
	 * 当我们使用immutable list的时候，这是一个Linked List
	 * 当我们使用给这个List添加元素的方法时，List本身不会改变，会返回一个包含新元素的新List
	 * 但是需要注意，这两个List都是LinkedList，且是在存储指向元素的引用
	 * 所以，万一真的对这两个结合中的任意一个元素做修改，另外一个集合中的元素其实都会变化，因为修改的是同一个对象
	 */
	val collection = List(Obj("Tie Wang"), Obj("Hua Li"))
	val newCollection = FunctionalPractice.prepend(Obj{"Zihan Wang"}, collection)
	println(newCollection)
	newCollection(1).name = "Richard Tie Wang"
	println(collection)


	val res = newCollection.partition(_.name.contains("Wang"))

	println(res._1)
	println(res._2)

	/**
	 * 对于Map，Scala一样提供的 ++ 和+ 方法
	 * 但是++是把两个Map合并成一个新的Map，+是把一个Map与另外一个键值对合并为一个新的Map
	 */
	val tieMap = Map("Richard" -> "Tie Wang")
	val huaMap = Map("Chloe" -> "Hua Li")
	val baseFamily = tieMap ++ huaMap
	val wholeFamily = baseFamily + ("Zoe" -> "Zihan Wang")
	println(wholeFamily)

	/**
	 * 对于 集合。++ 和 -- 代表合并两个集合 和 从一个集合中去掉部分元素， 都不会在原集合上操作，而是创建了新的集合
	 * 同理，对于 + 和 - 代表的分别是 添加一个元素 和 去掉一个元素
	 */


	/**
	 * filter 操作
	 *
	 * drop 删掉前n个元素
	 * dropWhile 删掉满足条件的连续前几个元素，直到找到第一个不满足的元素
	 * take 和 takeWhile就正好是drop与dropWhile的反向操作了
	 *
	 * filter 不用说了，只不过需要知道的是 它有一个反向操作叫 filterNot
	 * forall 是判断集合汇总元素是否全都符合条件
	 *
	 * find 找到符合要求的第一个元素
	 * exists 判断集合中是否有满足条件的元素
	 *
	 * partition 把元素按照是否符合条件，分成两个TraversableLike的实例
	 */


	/**
	 *
	 * foldLeft 与 foldRight 可以返回与集合中的对象类型无关的对象，相当于把map和reduce一起做了哦
	 *
	 *
	 * 注意，关于reduce，如果我们不知道集合是否是空
	 * 可以使用optionReduce, reduceRightOption和reduceLeftOption
	 * 这三个方法在集合为空时，返回None，否则返回Some，我们可以用Option来接受结果对象
	 */
}

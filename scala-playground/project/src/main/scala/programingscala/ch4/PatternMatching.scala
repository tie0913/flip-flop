package programmingscala.ch4

object PatternMatching {


	/**
	 * 基本的应用就类似于Java中的Switch Case语句
	 * 但实际的使用要灵活一些
	 *
	 *
	 * 使用模式匹配时
	 * 如果小写字母开头，则被认为是一个变量，会把x复制给当前变量
	 * 如果大写字母开头，则被认为是一个类型，此时如果给定的格式像下面 d:Double，当满足Double类型的转换条件后，就把x转换成Double并复制给d
	 *
	 * 当然我们也可以来个tuple或者seq，然后就直接给两个变量，就像case (a, b)那样
	 *
	 * _ 或者写 unexpected 都是 Java中的default语句
	 */
	def matching(x:Any) :String = x match{
		case Int => s"The Int value is $x"
		case d:Double => s"We have a double value which is $d"
		case s:String => s"String with its value $x is my favourite"
		case (a, b) => s"We have  tuple with its value $x"
		case _ => "I am not sure what it is "
	}

	/**
	 *
	 * 对于序列
	 *    +: 头插
	 *    :+ 尾插
	 *    :: 另外一种头插
	 *    ::: 链接两个序列
	 *
	 * @param seq
	 * @tparam T
	 * @return
	 */
	def matchingSeq[T](seq:Seq[T]):String = seq match {
		case head +: tail => s"Header is $head and " + matchingSeq(tail)
		case _ => "Nil"
	}

	def matchSeq():Unit = {

		val seq = Seq(List(1,2,3,4), List("a", "b"))

		seq.foreach {
			case a: Seq[Int] => println("This is Seq[Int]")
			case b: Seq[String] => println("This is Seq[String]")
			case _ => println("This is unknown")
		}

	}
}

object Run extends App{
	PatternMatching.matchSeq()
}

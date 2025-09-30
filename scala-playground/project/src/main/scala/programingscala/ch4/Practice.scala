package programmingscala.ch4

import programmingscala.ch4.Practice.withRetry

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

case class Log(level:String, message:String, timestamp:Long)

object Practice {


	def filterAndFormat(logs:Seq[Log], level:String):Seq[String] = logs.collect({case Log(l, m, _) if l == level => m})


	@tailrec
	def withRetry[T](times:Int)(block: => T):Option[T] = {
		Try(
			block
		)match{
			case Success(value) => Some(value)
			case Failure(_) if times > 0 => withRetry(times - 1)(block)
			case _ => None
		}
	}

	def calculate(exprs: Seq[Expr]): Int = {
		exprs.foldLeft(Option.empty[Int]){
			case (acc, Div(_, 0)) =>
				println("Denominator is zero")
				acc
			case (acc, Add(a, b)) => Some(acc.getOrElse(a) + b)
			case (acc, Mul(a, b)) => Some(acc.getOrElse(a) * b)
			case (acc, Sub(a, b)) => Some(acc.getOrElse(a) - b)
			case (acc, Div(a, b)) => Some(acc.getOrElse(a) / b)
		}.getOrElse(0)
	}

	def describePerson(format: (String, Int) => String): ((String, Int)) => String = {
		(p:(String, Int)) => format(p._1, p._2)
			/*
		val f:((String, Int)) => String = (p:(String, Int)) => format(p._1, p._2)
		f
		 */
	}
}

sealed trait Expr{
	def a:Int
	def b:Int
}
case class Add(a:Int, b:Int) extends Expr
case class Mul(a: Int, b: Int) extends Expr
case class Div(a: Int, b: Int) extends Expr
case class Sub(a: Int, b: Int) extends Expr





object Main extends App{
	val logs = Seq(
		Log("INFO", "Start process", 1000),
		Log("ERROR", "Something failed", 2000),
		Log("INFO", "Process finished", 3000)
	)
	println(Practice.filterAndFormat(logs, "INFO"))

	println(withRetry(3)({
		println("Hello World")
		"Done"
	}))
}
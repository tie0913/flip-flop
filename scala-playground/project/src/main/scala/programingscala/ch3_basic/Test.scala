package programmingscala.ch3_basic

import scala.util.{Failure, Success, Try}

object Chapter3{
	def square(x:Int):Int = x * x
	val f:Int=>Int = square

	def applyTwice(f:Int => Int, x:Int): Int = {
		f(f(x))
	}

	applyTwice(square, 2)

	val addOne:(Int) => Int = (x:Int) => x + 1
	val addOneSimple:(Int) => Int = _ + 1

	val l = List(1,2,3)
	println(l.map(addOne))
	println(l.map(addOneSimple))

	def longTime[T](f: => T):(T, Long) = {
		val start = System.currentTimeMillis()
		(f, System.currentTimeMillis() - start)
	}
	println(longTime{
		Thread.sleep(500)
	}._2)


	def makeAddr(x:Int): Int => Int = _ + x
	val add5 = makeAddr(5)
	println(add5(10))


	def multiple(a:Int)(b:Int): Int = a * b
	val times2 = multiple(2) _
	println(times2(10))
}

class Transaction(txManager: Any){

	private def begin():Unit = {
		println("Tx is began")
	}

	private def rollback():Unit = {
		println("Tx has been rollback")
	}

	private def commit():Unit = {
		println("Tx has been commited")
	}

	def withTx[T](service: => T):Option[T] = {

		Try({
			begin()
			service
		})match {
			case Success(value) =>
				commit()
				Some(value)
			case Failure(exception) =>
				rollback()
				None
		}

		/*
		try{
			begin()
			val res = service
			commit()
			Some(res)
		}catch {
			case e: RuntimeException => {
				rollback()
				None
			}
		}*/
	}
}
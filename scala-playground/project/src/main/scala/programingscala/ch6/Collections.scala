package programmingscala.ch6

import java.time.Clock
import scala.util.Random

class Collections {

}


class SeqDemo{

	/**
	 * 我们可以对Seq的子类做拼接
	 *
	 * 方法有。::, :+, +:, ++
	 *
	 * 对于:+和+: 的速记方法 冒号永远在集合一边，元素在加号那边
	 *
	 * 注意：Immutable Seq里有一个Vector，这个东西是bit map tree
	 * 随机访问效率接近O(1)，而且也是基于链表实现的，可以用来作为数组或不可变的ArrayList来用
	 * 从而解决了基于LinkedList实现的默认List在随机访问湿效率过低的问题
	 */

	def compare():Unit = {

		val seed = 1000000
		val range = 0 to seed
		val list = range.toList
		val vector = range.toVector

		val index = Random.nextInt(seed)

		println(s"$seed, $index")
		val s1 = System.nanoTime()
		list(index)
		val s2 = System.nanoTime()
		vector(index)
		val s3 = System.nanoTime()

		println(s2 - s1)
		println(s3 - s2)

	}

}

object CollectionsTest extends App{

	new SeqDemo().compare()

}


import scala.collection.immutable.{HashSet => ImmutableHashSet}

object SequentialHashTrieReduce extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object SequentialHashTrieForeach extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object SequentialHashTrieForall extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object SequentialHashTrieFind extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection find {
			x => x == collection.size - 1
		}
	}
}

object SequentialHashTrieFilter extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection filter {
			x => x < collection.size / 2
		}
	}
}

object SequentialHashTrieFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object SequentialHashTrieFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object SequentialHashTrieFold extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object SequentialHashTrieMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object SequentialHashTrieFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection flatMap {
			x:Int => ImmutableHashSet(x + 1)
		}
	}
}

object SequentialHashTriePartition extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.size / 2
		}
	}
}

object SequentialHashTrieSpan extends scala.testing.Benchmark {
	var length = 250000
	var collection: ImmutableHashSet[Int] = ImmutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = ImmutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection span {
			x:Int => x < collection.size / 2
		}
	}
}
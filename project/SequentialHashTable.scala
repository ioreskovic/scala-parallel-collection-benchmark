
import scala.collection.mutable.{HashSet => MutableHashSet}

object SequentialHashTableReduce extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object SequentialHashTableForeach extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object SequentialHashTableForall extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object SequentialHashTableFind extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection find {
			x => x == collection.size - 1
		}
	}
}

object SequentialHashTableFilter extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection filter {
			x => x < collection.size / 2
		}
	}
}

object SequentialHashTableFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object SequentialHashTableFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object SequentialHashTableFold extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object SequentialHashTableMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object SequentialHashTableFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection flatMap {
			x:Int => Array(x + 1)
		}
	}
}

object SequentialHashTablePartition extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.size / 2
		}
	}
}

object SequentialHashTableSpan extends scala.testing.Benchmark {
	var length = 250000
	var collection: MutableHashSet[Int] = MutableHashSet.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = MutableHashSet((0 until length): _*)
	}
	
	def run = {
		collection span {
			x:Int => x < collection.size / 2
		}
	}
}

object SequentialArrayReduce extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]

	length = sys.props("length").toInt
	init
	
	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object SequentialArrayForeach extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object SequentialArrayForall extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object SequentialArrayFind extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection find {
			x => x == collection.length - 1
		}
	}
}

object SequentialArrayFilter extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection filter {
			x => x < collection.length / 2
		}
	}
}

object SequentialArrayFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object SequentialArrayFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object SequentialArrayFold extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object SequentialArrayMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object SequentialArrayFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection flatMap {
			x:Int => Array(x + 1)
		}
	}
}

object SequentialArrayPartition extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.length / 2
		}
	}
}

object SequentialArraySpan extends scala.testing.Benchmark {
	var length = 250000
	var collection: Array[Int] = Array.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Array.range(0, length)
	}
	
	def run = {
		collection span {
			x:Int => x < collection.length / 2
		}
	}
}
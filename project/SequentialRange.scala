
object SequentialRangeReduce extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object SequentialRangeForeach extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object SequentialRangeForall extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object SequentialRangeFind extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection find {
			x => x == collection.length - 1
		}
	}
}

object SequentialRangeFilter extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection filter {
			x => x < collection.length / 2
		}
	}
}

object SequentialRangeFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object SequentialRangeFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object SequentialRangeFold extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object SequentialRangeMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object SequentialRangeFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection flatMap {
			x:Int => Range(x + 1, x + 2, 1)
		}
	}
}

object SequentialRangePartition extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.length / 2
		}
	}
}

object SequentialRangeSpan extends scala.testing.Benchmark {
	var length = 250000
	var collection: Range = Range(0,0)
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Range(0, length, 1)
	}
	
	def run = {
		collection span {
			x:Int => x < collection.length / 2
		}
	}
}
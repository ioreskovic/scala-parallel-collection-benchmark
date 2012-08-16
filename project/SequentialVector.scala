
object SequentialVectorReduce extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object SequentialVectorForeach extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object SequentialVectorForall extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object SequentialVectorFind extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection find {
			x => x == collection.length - 1
		}
	}
}

object SequentialVectorFilter extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection filter {
			x => x < collection.length / 2
		}
	}
}

object SequentialVectorFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object SequentialVectorFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object SequentialVectorFold extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object SequentialVectorMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object SequentialVectorFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection flatMap {
			x:Int => Array(x + 1)
		}
	}
}

object SequentialVectorPartition extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.length / 2
		}
	}
}

object SequentialVectorSpan extends scala.testing.Benchmark {
	var length = 250000
	var collection: Vector[Int] = Vector.empty[Int]
	
	length = sys.props("length").toInt
	init

	def init() {
		collection = Vector((0 until length): _*)
	}
	
	def run = {
		collection span {
			x:Int => x < collection.length / 2
		}
	}
}
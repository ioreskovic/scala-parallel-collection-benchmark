
import scala.collection.parallel.immutable.ParRange
import scala.collection.parallel.ForkJoinTaskSupport
import scala.concurrent.forkjoin.ForkJoinPool
import scala.collection.parallel.ForkJoinTasks

object ParallelRangeReduce extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object ParallelRangeForeach extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object ParallelRangeForall extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object ParallelRangeFind extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection find {
			x => x == collection.length - 1
		}
	}
}

object ParallelRangeFilter extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection filter {
			x => x < collection.length / 2
		}
	}
}

object ParallelRangeFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object ParallelRangeFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object ParallelRangeFold extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object ParallelRangeMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object ParallelRangeFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection flatMap {
			x:Int => ParRange(x + 1, x + 2, 1, false)
		}
	}
}

object ParallelRangePartition extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.length / 2
		}
	}
}

object ParallelRangeSpan extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParRange = Range(0,0).par
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParRange(0, length, 1, false)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection span {
			x:Int => x < collection.length / 2
		}
	}
}
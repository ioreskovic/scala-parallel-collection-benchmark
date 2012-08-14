
import scala.collection.parallel.mutable.ParArray
import scala.collection.parallel.ForkJoinTaskSupport
import scala.concurrent.forkjoin.ForkJoinPool
import scala.collection.parallel.ForkJoinTasks

object ParallelArrayReduce extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object ParallelArrayForeach extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object ParallelArrayForall extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object ParallelArrayFind extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection find {
			x => x == collection.length - 1
		}
	}
}

object ParallelArrayFilter extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection filter {
			x => x < collection.length / 2
		}
	}
}

object ParallelArrayFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object ParallelArrayFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object ParallelArrayFold extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object ParallelArrayMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object ParallelArrayFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection flatMap {
			x:Int => ParArray(x + 1)
		}
	}
}

object ParallelArrayPartition extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.length / 2
		}
	}
}

object ParallelArraySpan extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParArray[Int] = ParArray.empty[Int]
	
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init
	
	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParArray.range(0, length)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection span {
			x:Int => x < collection.length / 2
		}
	}
}
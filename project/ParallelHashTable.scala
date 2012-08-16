
import scala.collection.parallel.mutable.ParHashSet
import scala.collection.parallel.ForkJoinTaskSupport
import scala.concurrent.forkjoin.ForkJoinPool
import scala.collection.parallel.ForkJoinTasks

object ParallelHashTableReduce extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() = {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object ParallelHashTableForeach extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object ParallelHashTableForall extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object ParallelHashTableFind extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection find {
			x => x == collection.size - 1
		}
	}
}

object ParallelHashTableFilter extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection filter {
			x => x < collection.size / 2
		}
	}
}

object ParallelHashTableFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object ParallelHashTableFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object ParallelHashTableFold extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object ParallelHashTableMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object ParallelHashTableFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection flatMap {
			x:Int => Array(x + 1)
		}
	}
}

object ParallelHashTablePartition extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.size / 2
		}
	}
}

object ParallelHashTableSpan extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParHashSet[Int] = ParHashSet.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParHashSet((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection span {
			x:Int => x < collection.size / 2
		}
	}
}
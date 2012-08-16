
import scala.collection.parallel.immutable.ParHashSet
import scala.collection.parallel.ForkJoinTaskSupport
import scala.concurrent.forkjoin.ForkJoinPool
import scala.collection.parallel.ForkJoinTasks

object ParallelHashTrieReduce extends scala.testing.Benchmark {
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

object ParallelHashTrieForeach extends scala.testing.Benchmark {
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

object ParallelHashTrieForall extends scala.testing.Benchmark {
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

object ParallelHashTrieFind extends scala.testing.Benchmark {
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

object ParallelHashTrieFilter extends scala.testing.Benchmark {
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

object ParallelHashTrieFoldRight extends scala.testing.Benchmark {
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

object ParallelHashTrieFoldLeft extends scala.testing.Benchmark {
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

object ParallelHashTrieFold extends scala.testing.Benchmark {
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

object ParallelHashTrieMap extends scala.testing.Benchmark {
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

object ParallelHashTrieFlatMap extends scala.testing.Benchmark {
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

object ParallelHashTriePartition extends scala.testing.Benchmark {
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

object ParallelHashTrieSpan extends scala.testing.Benchmark {
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
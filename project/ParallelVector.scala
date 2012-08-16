
import scala.collection.parallel.immutable.ParVector
import scala.collection.parallel.ForkJoinTaskSupport
import scala.concurrent.forkjoin.ForkJoinPool
import scala.collection.parallel.ForkJoinTasks

object ParallelVectorReduce extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() = {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection reduce {
			(a, b) => a + b
		}
	}
}

object ParallelVectorForeach extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection foreach {
			x => x + 1
		}
	}
}

object ParallelVectorForall extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection forall {
			x => x < x + 1
		}
	}
}

object ParallelVectorFind extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection find {
			x => x == collection.length - 1
		}
	}
}

object ParallelVectorFilter extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection filter {
			x => x < collection.length / 2
		}
	}
}

object ParallelVectorFoldRight extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldRight(Int.MaxValue)( (x, y) => { if (x < y) x else y })
	}
}

object ParallelVectorFoldLeft extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.foldLeft(Int.MinValue)( (x, y) => { if (x > y) x else y })
	}
}

object ParallelVectorFold extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection.fold(0)(_+_)
	}
}

object ParallelVectorMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection map {
			x => x + 1
		}
	}
}

object ParallelVectorFlatMap extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init

	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection flatMap {
			x:Int => Array(x + 1)
		}
	}
}

object ParallelVectorPartition extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init
	
	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection partition {
			x:Int => x < collection.length / 2
		}
	}
}

object ParallelVectorSpan extends scala.testing.Benchmark {
	var length = 250000
	var par = 4
	var collection: ParVector[Int] = ParVector.empty[Int]
		
	length = sys.props("length").toInt
	par = sys.props("par").toInt
	init
	
	def init() {
		ForkJoinTasks.defaultForkJoinPool.setParallelism(par)
		collection = ParVector((0 until length): _*)
		// collection.tasksupport = new ForkJoinTaskSupport(new ForkJoinPool(par))
	}
	
	def run = {
		collection span {
			x:Int => x < collection.length / 2
		}
	}
}

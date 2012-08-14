package com.google.summer

import sbt._
import Keys._

import scala.collection.mutable.{ HashMap => MutableHashMap }
import scala.util.Properties._
import java.io.{ File => JFile }

object BenchmarkBuild extends Build {
	
	val Benchmark = config("benchmark") extend (Compile)
	
	val benchmarkSettings = Defaults.defaultSettings ++ Seq(
		organization := "com.google.summer",
		name         := "scala-parallel-collection-benchmark",
		version      := "1.0.0",
		scalaVersion := "2.9.1"
	)
	
	final val defaultParallelizationLevel = 4
	final val defaultCollectionLength     = 250000
	final val defaultNumberOfIterations   = 10
	final val defaultIterationMultiplier  = 10
	
	lazy val classesDirPath = new JFile("project/target/scala-2.9.1/sbt-0.11.3/classes").getAbsolutePath
	lazy val scalaLibDirPath = new JFile(userHome + "/.sbt/boot/scala-2.9.1/lib/scala-library.jar").getAbsolutePath
	
	val probe = InputKey[Unit]("probe")
	
	/* Sequential Array - scala.Array */
	val sequentialArrayFilter    = InputKey[Unit]("sequential-array-filter"    )
	val sequentialArrayFind      = InputKey[Unit]("sequential-array-find"      )
	val sequentialArrayFlatMap   = InputKey[Unit]("sequential-array-flat-map"  )
	val sequentialArrayFold      = InputKey[Unit]("sequential-array-fold"      )
	val sequentialArrayFoldLeft  = InputKey[Unit]("sequential-array-fold-left" )
	val sequentialArrayFoldRight = InputKey[Unit]("sequential-array-fold-right")
	val sequentialArrayForall    = InputKey[Unit]("sequential-array-forall"    )
	val sequentialArrayForeach   = InputKey[Unit]("sequential-array-foreach"   )
	val sequentialArrayMap       = InputKey[Unit]("sequential-array-map"       )
	val sequentialArrayPartition = InputKey[Unit]("sequential-array-partition" )
	val sequentialArrayReduce    = InputKey[Unit]("sequential-array-reduce"    )
	val sequentialArraySpan      = InputKey[Unit]("sequential-array-span"      )
	
	/* Parallel Array - scala.collection.parallel.mutable.ParArray */
	val parallelArrayFilter    = InputKey[Unit]("parallel-array-filter"    )
	val parallelArrayFind      = InputKey[Unit]("parallel-array-find"      )
	val parallelArrayFlatMap   = InputKey[Unit]("parallel-array-flat-map"  )
	val parallelArrayFold      = InputKey[Unit]("parallel-array-fold"      )
	val parallelArrayFoldLeft  = InputKey[Unit]("parallel-array-fold-left" )
	val parallelArrayFoldRight = InputKey[Unit]("parallel-array-fold-right")
	val parallelArrayForall    = InputKey[Unit]("parallel-array-forall"    )
	val parallelArrayForeach   = InputKey[Unit]("parallel-array-foreach"   )
	val parallelArrayMap       = InputKey[Unit]("parallel-array-map"       )
	val parallelArrayPartition = InputKey[Unit]("parallel-array-partition" )
	val parallelArrayReduce    = InputKey[Unit]("parallel-array-reduce"    )
	val parallelArraySpan      = InputKey[Unit]("parallel-array-span"      )
	
	/* Sequential Vector - scala.collection.immutable.Vector */
	val sequentialVectorFilter    = InputKey[Unit]("sequential-vector-filter"    )
	val sequentialVectorFind      = InputKey[Unit]("sequential-vector-find"      )
	val sequentialVectorFlatMap   = InputKey[Unit]("sequential-vector-flat-map"  )
	val sequentialVectorFold      = InputKey[Unit]("sequential-vector-fold"      )
	val sequentialVectorFoldLeft  = InputKey[Unit]("sequential-vector-fold-left" )
	val sequentialVectorFoldRight = InputKey[Unit]("sequential-vector-fold-right")
	val sequentialVectorForall    = InputKey[Unit]("sequential-vector-forall"    )
	val sequentialVectorForeach   = InputKey[Unit]("sequential-vector-foreach"   )
	val sequentialVectorMap       = InputKey[Unit]("sequential-vector-map"       )
	val sequentialVectorPartition = InputKey[Unit]("sequential-vector-partition" )
	val sequentialVectorReduce    = InputKey[Unit]("sequential-vector-reduce"    )
	val sequentialVectorSpan      = InputKey[Unit]("sequential-vector-span"      )
	
	/* Parallel Vector - scala.collection.parallel.immutable.ParVector */
	val parallelVectorFilter    = InputKey[Unit]("parallel-vector-filter"    )
	val parallelVectorFind      = InputKey[Unit]("parallel-vector-find"      )
	val parallelVectorFlatMap   = InputKey[Unit]("parallel-vector-flat-map"  )
	val parallelVectorFold      = InputKey[Unit]("parallel-vector-fold"      )
	val parallelVectorFoldLeft  = InputKey[Unit]("parallel-vector-fold-left" )
	val parallelVectorFoldRight = InputKey[Unit]("parallel-vector-fold-right")
	val parallelVectorForall    = InputKey[Unit]("parallel-vector-forall"    )
	val parallelVectorForeach   = InputKey[Unit]("parallel-vector-foreach"   )
	val parallelVectorMap       = InputKey[Unit]("parallel-vector-map"       )
	val parallelVectorPartition = InputKey[Unit]("parallel-vector-partition" )
	val parallelVectorReduce    = InputKey[Unit]("parallel-vector-reduce"    )
	val parallelVectorSpan      = InputKey[Unit]("parallel-vector-span"      )
	
	/* Sequential Range - scala.collection.immutable.Range */
	val sequentialRangeFilter    = InputKey[Unit]("sequential-range-filter"    )
	val sequentialRangeFind      = InputKey[Unit]("sequential-range-find"      )
	val sequentialRangeFlatMap   = InputKey[Unit]("sequential-range-flat-map"  )
	val sequentialRangeFold      = InputKey[Unit]("sequential-range-fold"      )
	val sequentialRangeFoldLeft  = InputKey[Unit]("sequential-range-fold-left" )
	val sequentialRangeFoldRight = InputKey[Unit]("sequential-range-fold-right")
	val sequentialRangeForall    = InputKey[Unit]("sequential-range-forall"    )
	val sequentialRangeForeach   = InputKey[Unit]("sequential-range-foreach"   )
	val sequentialRangeMap       = InputKey[Unit]("sequential-range-map"       )
	val sequentialRangePartition = InputKey[Unit]("sequential-range-partition" )
	val sequentialRangeReduce    = InputKey[Unit]("sequential-range-reduce"    )
	val sequentialRangeSpan      = InputKey[Unit]("sequential-range-span"      )
	
	/* Parallel Range - scala.collection.parallel.immutable.ParRange */
	val parallelRangeFilter    = InputKey[Unit]("parallel-range-filter"    )
	val parallelRangeFind      = InputKey[Unit]("parallel-range-find"      )
	val parallelRangeFlatMap   = InputKey[Unit]("parallel-range-flat-map"  )
	val parallelRangeFold      = InputKey[Unit]("parallel-range-fold"      )
	val parallelRangeFoldLeft  = InputKey[Unit]("parallel-range-fold-left" )
	val parallelRangeFoldRight = InputKey[Unit]("parallel-range-fold-right")
	val parallelRangeForall    = InputKey[Unit]("parallel-range-forall"    )
	val parallelRangeForeach   = InputKey[Unit]("parallel-range-foreach"   )
	val parallelRangeMap       = InputKey[Unit]("parallel-range-map"       )
	val parallelRangePartition = InputKey[Unit]("parallel-range-partition" )
	val parallelRangeReduce    = InputKey[Unit]("parallel-range-reduce"    )
	val parallelRangeSpan      = InputKey[Unit]("parallel-range-span"      )
	
	/* Sequential Hash Table - scala.collection.mutable.HashSet */
	val sequentialHashtableFilter    = InputKey[Unit]("sequential-hashtable-filter"    )
	val sequentialHashtableFind      = InputKey[Unit]("sequential-hashtable-find"      )
	val sequentialHashtableFlatMap   = InputKey[Unit]("sequential-hashtable-flat-map"  )
	val sequentialHashtableFold      = InputKey[Unit]("sequential-hashtable-fold"      )
	val sequentialHashtableFoldLeft  = InputKey[Unit]("sequential-hashtable-fold-left" )
	val sequentialHashtableFoldRight = InputKey[Unit]("sequential-hashtable-fold-right")
	val sequentialHashtableForall    = InputKey[Unit]("sequential-hashtable-forall"    )
	val sequentialHashtableForeach   = InputKey[Unit]("sequential-hashtable-foreach"   )
	val sequentialHashtableMap       = InputKey[Unit]("sequential-hashtable-map"       )
	val sequentialHashtablePartition = InputKey[Unit]("sequential-hashtable-partition" )
	val sequentialHashtableReduce    = InputKey[Unit]("sequential-hashtable-reduce"    )
	val sequentialHashtableSpan      = InputKey[Unit]("sequential-hashtable-span"      )
	
	/* Parallel Hash Table - scala.collection.parallel.mutable.ParHashSet */
	val parallelHashtableFilter    = InputKey[Unit]("parallel-hashtable-filter"    )
	val parallelHashtableFind      = InputKey[Unit]("parallel-hashtable-find"      )
	val parallelHashtableFlatMap   = InputKey[Unit]("parallel-hashtable-flat-map"  )
	val parallelHashtableFold      = InputKey[Unit]("parallel-hashtable-fold"      )
	val parallelHashtableFoldLeft  = InputKey[Unit]("parallel-hashtable-fold-left" )
	val parallelHashtableFoldRight = InputKey[Unit]("parallel-hashtable-fold-right")
	val parallelHashtableForall    = InputKey[Unit]("parallel-hashtable-forall"    )
	val parallelHashtableForeach   = InputKey[Unit]("parallel-hashtable-foreach"   )
	val parallelHashtableMap       = InputKey[Unit]("parallel-hashtable-map"       )
	val parallelHashtablePartition = InputKey[Unit]("parallel-hashtable-partition" )
	val parallelHashtableReduce    = InputKey[Unit]("parallel-hashtable-reduce"    )
	val parallelHashtableSpan      = InputKey[Unit]("parallel-hashtable-span"      )
	
	/* Sequential Hash Trie - scala.collection.immutable.HashSet */
	val sequentialHashtrieFilter    = InputKey[Unit]("sequential-hashtrie-filter"    )
	val sequentialHashtrieFind      = InputKey[Unit]("sequential-hashtrie-find"      )
	val sequentialHashtrieFlatMap   = InputKey[Unit]("sequential-hashtrie-flat-map"  )
	val sequentialHashtrieFold      = InputKey[Unit]("sequential-hashtrie-fold"      )
	val sequentialHashtrieFoldLeft  = InputKey[Unit]("sequential-hashtrie-fold-left" )
	val sequentialHashtrieFoldRight = InputKey[Unit]("sequential-hashtrie-fold-right")
	val sequentialHashtrieForall    = InputKey[Unit]("sequential-hashtrie-forall"    )
	val sequentialHashtrieForeach   = InputKey[Unit]("sequential-hashtrie-foreach"   )
	val sequentialHashtrieMap       = InputKey[Unit]("sequential-hashtrie-map"       )
	val sequentialHashtriePartition = InputKey[Unit]("sequential-hashtrie-partition" )
	val sequentialHashtrieReduce    = InputKey[Unit]("sequential-hashtrie-reduce"    )
	val sequentialHashtrieSpan      = InputKey[Unit]("sequential-hashtrie-span"      )
	
	/* Parallel Hash Trie - scala.collection.parallel.immutable.ParHashSet */
	val parallelHashtrieFilter    = InputKey[Unit]("parallel-hashtrie-filter"    )
	val parallelHashtrieFind      = InputKey[Unit]("parallel-hashtrie-find"      )
	val parallelHashtrieFlatMap   = InputKey[Unit]("parallel-hashtrie-flat-map"  )
	val parallelHashtrieFold      = InputKey[Unit]("parallel-hashtrie-fold"      )
	val parallelHashtrieFoldLeft  = InputKey[Unit]("parallel-hashtrie-fold-left" )
	val parallelHashtrieFoldRight = InputKey[Unit]("parallel-hashtrie-fold-right")
	val parallelHashtrieForall    = InputKey[Unit]("parallel-hashtrie-forall"    )
	val parallelHashtrieForeach   = InputKey[Unit]("parallel-hashtrie-foreach"   )
	val parallelHashtrieMap       = InputKey[Unit]("parallel-hashtrie-map"       )
	val parallelHashtriePartition = InputKey[Unit]("parallel-hashtrie-partition" )
	val parallelHashtrieReduce    = InputKey[Unit]("parallel-hashtrie-reduce"    )
	val parallelHashtrieSpan      = InputKey[Unit]("parallel-hashtrie-span"      )
	
	def getUsageForSequential(collectionMethod: String): String = {
		val usage = Seq("Usage: benchmark:" + collectionMethod + " [<collection length> [<benchmark iterations> [<iterations multiplier>]]]",
			"",
		    "Default values:",
			" - Collection Length:     " + defaultCollectionLength,
			" - Benchmark Iterations:  " + defaultNumberOfIterations,
			" - Iterations Multiplier: " + defaultIterationMultiplier
		)
		
		usage.mkString("\n")
	}
	
	def getUsageForParallel(collectionMethod: String): String = {
		val usage = Seq("Usage: benchmark:" + collectionMethod + " [<parallelization level> [<collection length> [<benchmark iterations> [<iterations multiplier>]]]]",
			"",
		    "Default values:",
			" - Parallelization Level: " + defaultParallelizationLevel,
			" - Collection Length:     " + defaultCollectionLength,
			" - Benchmark Iterations:  " + defaultNumberOfIterations,
			" - Iterations Multiplier: " + defaultIterationMultiplier
		)
		
		usage.mkString("\n")
	}
	
	def getWarningForParallel(): String = {
		val warning = Seq(
			"Warning!",
			"",
			"You are running the benchmark with default values:",
			" - Parallelization Level: " + defaultParallelizationLevel,
			" - Collection Length:     " + defaultCollectionLength,
			" - Benchmark Iterations:  " + defaultNumberOfIterations,
			" - Iterations Multiplier: " + defaultIterationMultiplier,
			""
		)
		
		warning.mkString("\n")
	}
	
	def getWarningForSequential(): String = {
		val warning = Seq(
			"Warning!",
			"",
			"You are running the benchmark with default values:",
			" - Collection Length:     " + defaultCollectionLength,
			" - Benchmark Iterations:  " + defaultNumberOfIterations,
			" - Iterations Multiplier: " + defaultIterationMultiplier,
			""
		)
		
		warning.mkString("\n")
	}
	
	def getSeqArgMap(args: Seq[String]): MutableHashMap[String, Int] = {
		val argsMap = new MutableHashMap[String, Int]
	
		if (args.length > 0) {
			argsMap.put("Collection Size", args(0).toInt)
		} else {
			argsMap.put("Collection Size", defaultCollectionLength)
		}
		
		if (args.length > 1) {
			argsMap.put("Benchmark Iterations", args(1).toInt)
		} else {
			argsMap.put("Benchmark Iterations", defaultNumberOfIterations)
		}
		
		if (args.length > 2) {
			argsMap.put("Iterations Multiplier", args(2).toInt)
		} else {
			argsMap.put("Iterations Multiplier", defaultIterationMultiplier)
		}
		
		argsMap
	}
	
	def getParArgMap(args: Seq[String]): MutableHashMap[String, Int] = {
		val argsMap = new MutableHashMap[String, Int]
		
		if (args.length > 0) {
			argsMap.put("Parallelization Level", args(0).toInt)
		} else {
			argsMap.put("Parallelization Level", defaultParallelizationLevel)
		}
		
		if (args.length > 1) {
			argsMap.put("Collection Size", args(1).toInt)
		} else {
			argsMap.put("Collection Size", defaultCollectionLength)
		}
		
		if (args.length > 2) {
			argsMap.put("Benchmark Iterations", args(2).toInt)
		} else {
			argsMap.put("Benchmark Iterations", defaultNumberOfIterations)
		}
		
		if (args.length > 3) {
			argsMap.put("Iterations Multiplier", args(3).toInt)
		} else {
			argsMap.put("Iterations Multiplier", defaultIterationMultiplier)
		}
		
		argsMap
	}
	
	def getReportFor(collection: String, method: String, argMap: MutableHashMap[String, Int], results: List[Long]): String = {
		val report = Seq(
			"Benchmark report for " + collection + "#" + method,
			"Benchmark parameters:",
			"NYI",
			"",
			"Best benchmark time: " + results.min + " ms",
			"All benchmark times:",
			results.mkString("", " ms\n", " ms")
		)
		
		report.mkString("\n")
	}
	
	def execCmd(command: String): String = {
		val result: String = (command !!)
		return result
	}
	
	def mkParCmd(par: Int, length: Int, iters: Int, multi: Int, clazz: String): String = {
		val parCmd = "java -server -cp " + classesDirPath + ";" + scalaLibDirPath + " -Dpar=" + par + " -Dlength=" + length + " " + clazz + " " + iters + " " + multi
		
		return parCmd
	}
	
	def mkSeqCmd(length: Int, iters: Int, multi: Int, clazz: String): String = {
		val seqCmd = "java -server -cp " + classesDirPath + ";" + scalaLibDirPath + " -Dlength=" + length + " " + clazz + " " + iters + " " + multi
		
		return seqCmd
	}
	
	def prettyfy(result: String, classDesc: String, methodDesc: String): String = {
		val benchmarkTimes = result.trim.split("\\s+").drop(1).map(_.toInt)
		val benchmarkOutput = benchmarkTimes.mkString("", " ms\n", " ms")
		
		val lines = Seq(
			"Benchmark results",
			"=================",
			"Collection: " + classDesc,
			"Method:     " + methodDesc,
			"",
			"Best time: " + benchmarkTimes.min + " ms",
			"",
			"All times: ",
			benchmarkOutput
		)
		
		return lines.mkString("\n")
	}
	
	lazy val benchmarkProject = Project(
		"project",
		file("."),
		
		settings = benchmarkSettings ++ Seq(
			probe in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) => 
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					println("Scala Version: " + scalaVersion)
					println("Number of arguments: " + args.length)
					println(("ls -l" !!))
					println("Arguments:")
					args foreach {
						elem => println("\t" + elem)
					}
				}
			},
			
			/* Sequential Array - scala.Array */
			sequentialArrayFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayPartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayPartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArrayReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArrayReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialArraySpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-array-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialArraySpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Array"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			/* Parallel Array - scala.collection.parallel.mutable.ParArray */
			parallelArrayFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayPartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayPartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArrayReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArrayReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelArraySpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-array-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelArraySpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Array"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorPartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorPartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialVectorSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-vector-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialVectorSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Vector"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			
			parallelVectorFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorPartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorPartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelVectorSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-vector-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelVectorSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Vector"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangePartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangePartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialRangeSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-range-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialRangeSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Range"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			
			parallelRangeFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangePartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangePartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelRangeSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-range-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelRangeSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Range"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtablePartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTablePartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtableSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtable-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTableSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Table"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			
			parallelHashtableFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtablePartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTablePartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtableSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtable-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTableSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Table"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
						sequentialHashtrieFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtriePartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTriePartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForSequential())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			sequentialHashtrieSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 4) {
						println(getUsageForSequential("sequential-hashtrie-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getSeqArgMap(args)
						val command: String = mkSeqCmd( 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"SequentialHashTrieSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Sequential Hash Trie"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			
			parallelHashtrieFilter in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-filter"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieFilter"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Filter"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieFind in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-find"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieFind"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Find"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieFlatMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-flat-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieFlatMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Flat Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieFold in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-fold"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieFold"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Fold"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieFoldLeft in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-fold-left"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieFoldLeft"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Fold Left"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieFoldRight in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-fold-right"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieFoldRight"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Fold Right"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieForall in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-forall"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieForall"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Forall"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieForeach in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-foreach"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieForeach"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Foreach"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieMap in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-map"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieMap"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Map"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtriePartition in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-partition"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTriePartition"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Partition"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieReduce in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-reduce"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieReduce"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Reduce"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			},
			
			parallelHashtrieSpan in Benchmark <<= inputTask { (argTask: TaskKey[Seq[String]]) =>
				(argTask, scalaVersion) map { (args: Seq[String], scalaVersion: String) =>
					if ((args.length == 1 && (args(0).trim.equalsIgnoreCase("usage") || args(0).trim.equalsIgnoreCase("help"))) || args.length > 5) {
						println(getUsageForParallel("parallel-hashtrie-span"))
					} else {
						if (args.length == 0) {
							println(getWarningForParallel())
						}
						
						val argMap = getParArgMap(args)
						val command: String = mkParCmd( 
							argMap("Parallelization Level"), 
							argMap("Collection Size"), 
							argMap("Benchmark Iterations"), 
							argMap("Iterations Multiplier"), 
							"ParallelHashTrieSpan"
						)
						val result: String = execCmd(command)
						
						val classDesc = "Parallel Hash Trie"
						val methodDesc ="Span"
						
						val output: String = prettyfy(result, classDesc, methodDesc)
						println(output)
					}
				}
			}
		)
		
		
	)
	
}
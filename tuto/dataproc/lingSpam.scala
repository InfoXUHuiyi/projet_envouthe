//Import the libraries
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD

object lingSpam {

	final val conf:SparkConf=new SparkConf().setAppName("Spark").setMaster("local")
	final val sc:SparkContext=new SparkContext(conf)
		
	//Function "probaWordDir"
	def probaWordDir(sc:SparkContext)(filesDir:String)
	  :(RDD[(String, Double)], Long) = {
		// Read all the files in filesDir
		val files=sc.wholeTextFiles(filesDir)
		// Count the number of files
		val nbFiles=files.count
	   
		// Text file must be splitted into a set of unique words  
		// Filtering ".", ":", "," , " ", "/", """\""", "-", "'", "(", ")", "@","!", "?, "$", "*", "&" from the set o unique words
		// For each word must count the number of files in which it occurs.
		val badWords = List(".", ":", "," , " ", "/", "-", "'", "(", ")", "@","""\""","!", "?", "$", "*", "&")
		val wordDirOccurency=files.flatMap(e=>e._2.split("\\s+").distinct.filter(!badWords.contains(_)).map(m=>(m,e._1))).map(f=>(f._1,1)).reduceByKey(_+_)
		
		// Here we calculate the probability of occurence each word
		val probaWord = wordDirOccurency.map(x => (x._1, x._2.toDouble/nbFiles.toDouble))

		(probaWord, nbFiles) // couple returned by the function
	}

	//Function "computeMutualInformationFactor":
	def computeMutualInformationFactor(
		probaWC:RDD[(String, Double)],
		probaW:RDD[(String, Double)],
		probaC: Double,
		default: Double // default value when a probability is missing
	):RDD[(String, Double)] = {
		val uniPwcW = probaW.leftOuterJoin(probaWC)
		val defPwc = uniPwcW.map(m=>(m._1,(m._2._1,m._2._2.getOrElse(default))))
		val deno = defPwc.map(f=>(f._1, (f._2._1 * probaC, f._2._2)))
		val miFactor = deno.map(d=>(d._1, d._2._2 * (math.log(d._2._2 / d._2._1))/math.log(2)))

		miFactor
	}

//Main:
	def main(args: Array[String]): Unit= {
		
		// Computing the couple (probaWordSpam, nbFilesSpam) for the directory “spam”.
		val (pPresentGivenSpam, nSpam) = probaWordDir(sc)("gs://huiyi-sandbox/tmp/ling-spam/spam/*.txt")

		// Computing the couple (probaWordHam, nbFilesHam) for the directory “ham”
		val (pPresentGivenHam, nHam) = probaWordDir(sc)("gs://huiyi-sandbox/tmp/ling-spam/ham/*.txt")

		//Read the data:
		// Read the directories containing the data
		val filesSpam=sc.wholeTextFiles("gs://huiyi-sandbox/tmp/ling-spam/spam/*.txt")
		val filesHam=sc.wholeTextFiles("gs://huiyi-sandbox/tmp/ling-spam/ham/*.txt")
		val nbFilesSpam=filesSpam.count
		val nbFilesHam=filesHam.count

		val trueSpam = pPresentGivenSpam
		val trueHam = pPresentGivenHam
		val interProba = pPresentGivenSpam.intersection(pPresentGivenHam)
		val falseSpam = pPresentGivenSpam.subtract(interProba)//(false, spam).
		val falseHam = pPresentGivenHam.subtract(interProba)//(false, ham)

		val unionProba = pPresentGivenSpam.union(pPresentGivenHam)
		val probaW = unionProba
		val totalFiles = nbFilesSpam + nbFilesHam
		val probaDefault = 0.2/totalFiles
		val probaCSpam = 1/nbFilesSpam
		val probaCHam = 1/nbFilesHam

		val factorTHam = computeMutualInformationFactor(trueHam,probaW,probaCHam,probaDefault)
		val factorTSpam = computeMutualInformationFactor(trueSpam,probaW,probaCSpam,probaDefault)
		val factorFHam = computeMutualInformationFactor(falseHam,probaW,probaCHam,probaDefault)
		val factorFSpam = computeMutualInformationFactor(falseSpam,probaW,probaCSpam,probaDefault)

		// Taking print on screen the 20 top words for each case
		val unionWord = trueSpam.union(trueHam).union(falseSpam).union(falseHam)
		val topWord = unionWord.top(20)(Ordering[Double].on(t=>t._2))
		topWord.foreach(arg=>println(arg))

		// Storaging the topWord.txt
		val topWord1 = topWord.map(t=>(t._1+" "+t._2))
		val topWordRdd = sc.makeRDD(topWord1)
		val writerTopWord=topWordRdd.saveAsTextFile("gs://huiyi-sandbox/tmp/spark_output/topWord.txt")

	}
}
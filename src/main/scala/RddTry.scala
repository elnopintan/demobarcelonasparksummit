import org.apache.spark.rdd.RDD
import scala.util.{Failure, Success, Try}

/**
  * If processing a RDD produces exceptions, the same process can be stored into a a Try class.
  * That way it can run the whole process and store which items produced Failures. The calling args can
  * be added using a tuple of (args, Try) to see which arg triggered the error
  */
trait RddTry {

  def debugRDD(source: RDD[String]) = source.map(line => (line, Try(Municipality.fromCsv(line))))

  def countOfTry(value: RDD[(String, Try[Municipality])]) = value.map {
    case (_, Success(_)) => 'success
    case (_, Failure(_)) => 'failure
  }.countByValue

  def filterSuccess(value: RDD[(String, Try[Municipality])]) = value.collect {
    case (_, Success(data)) => data
  }
}

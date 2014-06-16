import org.apache.spark.rdd.RDD

trait RddWithoutTry {

  def readMunicipality(source: RDD[String]) = source.map(Municipality.fromCsv(_))

}

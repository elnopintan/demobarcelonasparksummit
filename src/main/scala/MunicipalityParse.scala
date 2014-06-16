import org.apache.spark.rdd.RDD

class MunicipalityParsingFunction(rdd: RDD[String]) extends RddTry {
  def toMunicipality: RDD[Municipality] = filterSuccess(debugRDD(rdd))
}

/**
  * Spark uses implicit def to extend RDD adding to them new methods bases on the type stored into the RDD.
  * That way of extending RDD can be used to make custom extensions.
  * Eg. importing the Demo object that includes the trait Munlicipality parse allow to extend RDD[String]
  * with MunicipalityParsingFunction methods
  *
  * {{{
  * import Demo._
  * sc.textFile("data/municipios_tabulado").toMuncipality
  *  }}}
  */
trait MunicipalityParse {

  implicit def municipalityParsingFunction(rdd: RDD[String]) = new MunicipalityParsingFunction(rdd)

}

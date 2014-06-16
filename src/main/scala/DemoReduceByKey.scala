import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import com.twitter.algebird.Operators._

/**
  * When executing complex aggregation (like building map or array out of aggregated elements) the most
  * direct approach is to use .groupByKey, but this method forces spark to rearrange the data before
  * the aggregation. On the other hand .reduceByKey can aggregate more efficiently by reducing inside a partition
  * and then combine that aggregation with the same function. In order to use reduceByKey, the type of the aggregation
  * and the aggregated value must be of the same type.
  * .reduceByKey can be used in combination of libraries of algebraic types like twitter's algebird
  */
trait DemoReduceByKey {
  def municipalitiesBySizesAndProvinces(municipalities: RDD[Municipality]) = municipalities.map {
    case Municipality(geometry, name, province) => (province, (name, (geometry.getArea / 1000000).round))
  }.groupByKey.mapValues { municipalities =>
    municipalities.foldLeft[Map[Long, List[String]]](Map()) { (municipalityMap, item) =>
      val (name, area) = item
      municipalityMap.updated(area, (municipalityMap.getOrElse(area, List()) :+ name))
    }
  }

  def municipalitiesReducing(municipalities: RDD[Municipality]) = municipalities.map {
    case Municipality(geometry, name, province) => (province, Map((geometry.getArea / 1000000).round -> List(name)))
  }.reduceByKey { (mapA, mapB) =>
    mapA.foldLeft(mapB) {(newMap, item) =>
      val (area, names) = item
      newMap.updated(area, (newMap.getOrElse(area, List()) ++ names))
    }
  }

  def municipalitiesSemiGroup(municipalities: RDD[Municipality]) = municipalities.map {
    case Municipality(geometry, name, province) => (province, Map((geometry.getArea / 1000000).round -> List(name)))
  }.reduceByKey(_ + _)
}


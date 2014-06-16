import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.io.WKTReader
import org.geotools.geometry.jts.JTSFactoryFinder

case class Municipality (geometry: Geometry, name: String, province: Int)

object Municipality {

  private val geometryFactory = JTSFactoryFinder.getGeometryFactory(null)

  def fromCsv(line: String): Municipality = {
    val Array(wkt, _, name, _, _, province) = line.split("\t",-1)
    Municipality(new WKTReader(geometryFactory).read(wkt), name, province.toInt)
  }
}

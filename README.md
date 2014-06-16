Tips and Tricks for Scala
=========================

This code was used in the demo of 'Experiences with Spark at Telefonica'
To run the demo run:

     sbt assembly

Then include the generated jar to the spark-shell with

     export ADD_JARS = <generated-jar>

To use the demo inside the console

    scala> import Demo._

to use RDD of Try

    scala> val rddTry = debugRDD(sc.textFile("data/municipality_sample"))

to use Implicit def

   scala> val municipalities = sc.textFile("data/municipality_sample").toMunicipality

tu use DemoReduceByKey

   scala> val grouped = municipalitiesBySizesAndProvinces(municipalities)
   scala> val reduced = municipalitiesReducing(municipalities)
   scala> val withAlgebird = municipalitiesSemiGroup(municipalities)

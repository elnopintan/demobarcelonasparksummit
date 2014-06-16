name := "demo"

version in ThisBuild := "0.0.1" + "-" + Common.revision

organization in ThisBuild := "es.tid"

scalaVersion in ThisBuild := "2.10.4"

scalacOptions in ThisBuild ++= Seq("-deprecation", "-feature")

javacOptions in ThisBuild ++= Seq("-source", "1.6", "-target", "1.6")

resolvers in ThisBuild ++= Seq(
  "osgeo" at "http://download.osgeo.org/webdav/geotools/org/geotools/"
)

libraryDependencies in ThisBuild ++= Seq(
  "org.apache.spark" %% "spark-core" % "0.9.1" % "compile,test,provided",
  "org.geotools" % "gt-main" % "10.4",
  "com.vividsolutions" % "jts" % "1.12",
  "com.twitter" %% "algebird-core" % "0.6.0"
)


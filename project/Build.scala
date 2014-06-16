import sbt._
import Keys._
import sbtassembly.Plugin._
import sbtassembly.Plugin.AssemblyKeys._
import sbtassembly.Plugin.{PathList, MergeStrategy}
import org.scalastyle.sbt._


object Build extends Build {

  def projectId(name: String) = s"demo-$name"

  val demoMergeStrategy = mergeStrategy in assembly := {
    case "reference.conf" => MergeStrategy.concat
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case m if m.toLowerCase.matches("meta-inf/services/.*") => MergeStrategy.concat
    case m if m.toLowerCase.matches("meta-inf/.*\\.sf$") => MergeStrategy.discard
    case _ => MergeStrategy.first
  }

  lazy val demoAssembly = assemblySettings ++ demoMergeStrategy

  lazy val root = (Project(id = projectId("root"), base = file("."))
    settings(demoAssembly: _*)
    )
}

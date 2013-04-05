import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "aeroclub"
    val appVersion      = "1.0"

    val appDependencies = Seq(
	    javaCore,
	    javaJdbc,
	    javaEbean,
      filters,
      anorm,
      "mysql" % "mysql-connector-java" % "5.1.21",
      "net.sf.flexjson" % "flexjson" % "2.1"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(

    )

}
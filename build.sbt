name := """ffff00"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, SbtWeb)


scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "org.avaje.ebeanorm" % "avaje-ebeanorm" % "4.6.2"
)


fork in run := false
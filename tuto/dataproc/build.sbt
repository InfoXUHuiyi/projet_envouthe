scalaVersion := "2.11.8"

name := "ling-spam"
organization := "dataproc.codelab"
version := "1.0"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value % "provided",
  "org.apache.spark" %% "spark-core" % "2.3.2" % "provided"
)
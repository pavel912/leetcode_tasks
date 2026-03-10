ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.2"

lazy val root = (project in file("."))
  .settings(
    name := "scala_solutions"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test

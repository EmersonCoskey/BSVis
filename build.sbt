name := "BSVis"

version := "0.1"

scalaVersion := "2.13.7"

/**********************************************************************************************************************/

enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)

/**********************************************************************************************************************/

scalaJSUseMainModuleInitializer := true

libraryDependencies ++= Seq(
	"com.github.japgolly.scalajs-react" %%% "core" % "2.0.0",

	"org.scala-js" %%% "scalajs-dom" % "2.0.0",
)

Compile / npmDependencies ++= Seq(
	"react" -> "17.0.2",
	"react-dom" -> "17.0.2")
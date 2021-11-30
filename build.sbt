name := "BSVis"

version := "0.1"

scalaVersion := "2.13.7"

/**********************************************************************************************************************/

enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")

/**********************************************************************************************************************/

scalaJSUseMainModuleInitializer := true

val ScalaJsReactVer = "2.0.0"

libraryDependencies ++= Seq(
	"org.scala-js" %%% "scalajs-dom" % "2.0.0",

	"org.typelevel" %%% "cats-core" % "2.6.1",
	"org.typelevel" %%% "cats-effect" % "3.2.9",

	"com.github.japgolly.scalajs-react" %%% "callback"                 % ScalaJsReactVer,
	"com.github.japgolly.scalajs-react" %%% "callback-ext-cats"        % ScalaJsReactVer,
	"com.github.japgolly.scalajs-react" %%% "callback-ext-cats_effect" % ScalaJsReactVer,

	// Optional extensions for Cats / Cats Effect
	// (Note: these need to come before "core")
	"com.github.japgolly.scalajs-react" %%% "core-ext-cats"        % ScalaJsReactVer,
	"com.github.japgolly.scalajs-react" %%% "core-ext-cats_effect" % ScalaJsReactVer,

	// Mandatory
	"com.github.japgolly.scalajs-react" %%% "core-bundle-cats_effect"  % ScalaJsReactVer,
)

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
	"io.circe" %% "circe-core",
	"io.circe" %% "circe-generic",
	"io.circe" %% "circe-parser"
).map(_ % circeVersion)

scalacOptions ++= Seq(
	"-P:bm4:no-filtering:y",
	"-P:bm4:no-map-id:y",
	"-P:bm4:no-tupling:y",
	"-P:bm4:implicit-patterns:y",
)

Compile / npmDependencies ++= Seq(
	"react" -> "17.0.2",
	"react-dom" -> "17.0.2")
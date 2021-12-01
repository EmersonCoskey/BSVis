package net.emersoncoskey.bsvis.components

import cats.effect.SyncIO
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.{BloqView, BloqViewContainer}
import net.emersoncoskey.bsvis.data.beatsaber._
import org.scalajs.dom
import io.circe._
import io.circe.generic.auto._, io.circe.syntax._
import io.circe.parser._
import net.emersoncoskey.bsvis.data.mapjson.NoteJson

import scala.collection.immutable.TreeMap

object App {
	def C: ScalaFnComponent[Unit, CtorType.Nullary] = Component

	val amogus: String = """[
	{
	    "_time": 46,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 46.5,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 47,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 47.5,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
	    "_time": 48,
	    "_lineIndex": 3,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 48.5,
	    "_lineIndex": 0,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 49,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 49.5,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
	    "_time": 50,
	    "_lineIndex": 3,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 7
	},
	{
	    "_time": 50.5,
	    "_lineIndex": 0,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 6
	},
	{
	    "_time": 51,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 2
	},
	{
	    "_time": 51.5,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 3
	},
	{
	    "_time": 52,
	    "_lineIndex": 3,
	    "_lineLayer": 1,
	    "_type": 1,
	    "_cutDirection": 5
	},
	{
	    "_time": 52.5,
	    "_lineIndex": 0,
	    "_lineLayer": 1,
	    "_type": 0,
	    "_cutDirection": 4
	},
	{
	    "_time": 53,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 53.5,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 54,
	    "_lineIndex": 2,
	    "_lineLayer": 2,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 54.5,
	    "_lineIndex": 1,
	    "_lineLayer": 2,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
	    "_time": 55,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 55.5,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 56,
	    "_lineIndex": 3,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 56.5,
	    "_lineIndex": 0,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
	    "_time": 57,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 57.5,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 58,
	    "_lineIndex": 3,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 58.5,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
	    "_time": 59,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 59.5,
	    "_lineIndex": 0,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 60,
	    "_lineIndex": 2,
	    "_lineLayer": 2,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 60.5,
	    "_lineIndex": 1,
	    "_lineLayer": 2,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
		"_time": 61,
		"_lineIndex": 3,
		"_lineLayer": 1,
		"_type": 1,
		"_cutDirection": 3
	},
	{
	    "_time": 61.5,
	    "_lineIndex": 0,
	    "_lineLayer": 1,
	    "_type": 0,
	    "_cutDirection": 2
	},
	{
	    "_time": 62,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 62.5,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 63,
	    "_lineIndex": 3,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 63.5,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 0
	    },
	{
	    "_time": 64,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 64.5,
	    "_lineIndex": 0,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 65,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 65.5,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
	    "_time": 66,
	    "_lineIndex": 3,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 66.5,
	    "_lineIndex": 0,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 67,
	    "_lineIndex": 1,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 0
	},
	{
	    "_time": 67.5,
	    "_lineIndex": 2,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 0
	},
	{
	    "_time": 68,
	    "_lineIndex": 3,
	    "_lineLayer": 0,
	    "_type": 1,
	    "_cutDirection": 1
	},
	{
	    "_time": 68.5,
	    "_lineIndex": 0,
	    "_lineLayer": 0,
	    "_type": 0,
	    "_cutDirection": 1
	},
	{
	    "_time": 69,
	    "_lineIndex": 2,
	    "_lineLayer": 2,
	    "_type": 1,
	    "_cutDirection": 0
	}
]"""

	val templateMapData: TreeMap[Double, MapFrame] = {
		val parsedJson = parse(amogus)
		val jsonObj = parsedJson match {
			case Right(a) => a
			case Left(_) => ???
		}
		val noteList = jsonObj.as[List[NoteJson]]

	}

	val Component: ScalaFnComponent[Unit, CtorType.Nullary] =
		ScalaFnComponent.withHooks[Unit]
		                .render(_ =>
		                    Visualizer.C(templateMapData)
		                ) //creates a DOM component and renders it
}
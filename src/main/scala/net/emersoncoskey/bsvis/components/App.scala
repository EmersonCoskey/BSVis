package net.emersoncoskey.bsvis.components

import cats.Monoid
import cats.effect.{IO, SyncIO}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.{BloqView, BloqViewContainer}
import net.emersoncoskey.bsvis.data.beatsaber._
import org.scalajs.dom
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._
import io.circe.syntax._
import net.emersoncoskey.bsvis.data.mapjson.NoteJson
import net.emersoncoskey.bsvis.data.time._
import sttp.client3._
import sttp.client3.impl.cats.FetchCatsBackend

import scala.collection.immutable.TreeMap

object App {
	def C: ScalaFnComponent[Unit, CtorType.Nullary] = Component

	val amogus: String = """[
	                       |        {
	                       |            "_time": 4.481476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 4.481476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 5.231476783752441,
	                       |            "_lineIndex": 3,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 0
	                       |        },
	                       |        {
	                       |            "_time": 5.981476783752441,
	                       |            "_lineIndex": 0,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 0
	                       |        },
	                       |        {
	                       |            "_time": 6.981476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 6.981476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 7.481476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 0
	                       |        },
	                       |        {
	                       |            "_time": 7.481476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 0
	                       |        },
	                       |        {
	                       |            "_time": 7.981476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 7.981476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 8.481476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 2,
	                       |            "_type": 0,
	                       |            "_cutDirection": 5
	                       |        },
	                       |        {
	                       |            "_time": 8.481476783752441,
	                       |            "_lineIndex": 3,
	                       |            "_lineLayer": 1,
	                       |            "_type": 1,
	                       |            "_cutDirection": 5
	                       |        },
	                       |        {
	                       |            "_time": 9.231476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 9.231476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 9.981476783752441,
	                       |            "_lineIndex": 0,
	                       |            "_lineLayer": 1,
	                       |            "_type": 0,
	                       |            "_cutDirection": 4
	                       |        },
	                       |        {
	                       |            "_time": 9.981476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 2,
	                       |            "_type": 1,
	                       |            "_cutDirection": 4
	                       |        },
	                       |        {
	                       |            "_time": 10.481476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 7
	                       |        },
	                       |        {
	                       |            "_time": 10.481476783752441,
	                       |            "_lineIndex": 3,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 7
	                       |        },
	                       |        {
	                       |            "_time": 10.981476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 4
	                       |        },
	                       |        {
	                       |            "_time": 10.981476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 4
	                       |        },
	                       |        {
	                       |            "_time": 11.481476783752441,
	                       |            "_lineIndex": 0,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 11.481476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 12.481476783752441,
	                       |            "_lineIndex": 0,
	                       |            "_lineLayer": 1,
	                       |            "_type": 0,
	                       |            "_cutDirection": 2
	                       |        },
	                       |        {
	                       |            "_time": 12.481476783752441,
	                       |            "_lineIndex": 3,
	                       |            "_lineLayer": 1,
	                       |            "_type": 1,
	                       |            "_cutDirection": 3
	                       |        },
	                       |        {
	                       |            "_time": 13.231476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 0,
	                       |            "_type": 0,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 13.231476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 0,
	                       |            "_type": 1,
	                       |            "_cutDirection": 1
	                       |        },
	                       |        {
	                       |            "_time": 13.981476783752441,
	                       |            "_lineIndex": 1,
	                       |            "_lineLayer": 2,
	                       |            "_type": 0,
	                       |            "_cutDirection": 0
	                       |        },
	                       |        {
	                       |            "_time": 13.981476783752441,
	                       |            "_lineIndex": 2,
	                       |            "_lineLayer": 2,
	                       |            "_type": 1,
	                       |            "_cutDirection": 0
	                       |        }]""".stripMargin


	def parseMapData(implicit mapFrameMonoid: Monoid[MapFrame[Bloq]]): TreeMap[Double, MapFrame[Bloq]] = {
		val noteJsonList = decode[List[NoteJson]](amogus)

		println("sus3")
		val notes = noteJsonList match {
			case Left(err) => throw err
			case Right(list) => list.groupBy(_._time)
			                        .map { case (k, v) => (Beats(k).toSeconds(222.22000122070312, Seconds(0), Beats(0), Beats(0.5)).time, v.map(_.toMapFrame).foldLeft(mapFrameMonoid.empty)(mapFrameMonoid.combine)) }
			                        .toSeq
		}

		println("sus")
		TreeMap(notes: _*)
	}

	val Component: ScalaFnComponent[Unit, CtorType.Nullary] =
		ScalaFnComponent.withHooks[Unit]
		                .render(_ => Visualizer.C(parseMapData)) //creates a DOM component and renders it
}
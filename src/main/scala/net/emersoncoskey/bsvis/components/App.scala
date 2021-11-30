package net.emersoncoskey.bsvis.components

import cats.effect.SyncIO
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.{BloqView, BloqViewContainer}
import net.emersoncoskey.bsvis.data.beatsaber._
import org.scalajs.dom

import scala.collection.immutable.TreeMap

object App {
	def C: ScalaFnComponent[Unit, CtorType.Nullary] = Component



	val templateMapData: TreeMap[Double, MapFrame] = {
		val value = MapFrame(Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)), Some(Note(Blue, D)))

		val elements = for {
			key <- 1 to 20
		} yield (key.toDouble, value)

		TreeMap(elements:_*)
	}

	val Component: ScalaFnComponent[Unit, CtorType.Nullary] =
		ScalaFnComponent.withHooks[Unit]
		                .render(_ =>
		                    Visualizer.C(templateMapData)
		                ) //creates a DOM component and renders it
}
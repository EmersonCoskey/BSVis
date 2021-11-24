package net.emersoncoskey.bsvis.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.{BloqView, BloqViewContainer}
import net.emersoncoskey.bsvis.data.beatsaber._
import org.scalajs.dom.window

import scala.collection.immutable.TreeMap

object App {
	def C: ScalaFnComponent[Unit, CtorType.Nullary] = Component

	val value = MapFrame(Some(Bomb), Some(Bomb), Some(Bomb), Some(Bomb),
		Some(Bomb), None, None, Some(Bomb),
		Some(Bomb), Some(Bomb), Some(Bomb), Some(Bomb))

	val templateMapData: TreeMap[Double, MapFrame] = {

		val elements = for { key <- 1 to 20 } yield key.toDouble * 4 -> value

		TreeMap(elements:_*)
	}

	val Component: ScalaFnComponent[Unit, CtorType.Nullary] =
		ScalaFnComponent.withHooks[Unit]
		                .render(_ =>
		                    BloqViewContainer.C(BloqViewContainer.Props(value, () => (window.performance.now() / 1000) % 2))
		                ) //creates a DOM component and renders it
}
package net.emersoncoskey.bsvis.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.{BloqView, BloqViewContainer}
import net.emersoncoskey.bsvis.data.beatsaber._

import scala.collection.immutable.TreeMap

object App {
	def C: ScalaFnComponent[Unit, CtorType.Nullary] = Component

	val templateMapData: TreeMap[Double, MapFrame] = {
		val value = MapFrame(Some(Bomb), Some(Bomb), Some(Bomb), Some(Bomb),
			Some(Bomb), Some(Bomb), Some(Bomb), Some(Bomb),
			Some(Bomb), Some(Bomb), Some(Bomb), Some(Bomb))
		val elements = for {key <- 1 to 20} yield key.toDouble * 4 -> value

		TreeMap(elements:_*)
	}

	val Component: ScalaFnComponent[Unit, CtorType.Nullary] =
		ScalaFnComponent.withHooks[Unit]
		                .render(_ =>
			                BloqViewContainer.C(BloqViewContainer.Props(MapFrame.Empty, () => 0))
		                )
}
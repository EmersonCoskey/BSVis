package net.emersoncoskey.bsvis.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.Hooks._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.BloqViewContainer
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.hooks._

import scala.collection.immutable.TreeMap

object Visualizer {
	def C: ScalaFnComponent[TreeMap[Double, MapFrame], CtorType.Props] = Component

	val Component: ScalaFnComponent[TreeMap[Double, MapFrame], CtorType.Props] =
		ScalaFnComponent.withHooks[TreeMap[Double, MapFrame]]
		                .useRef[Double](0.0)
		                .useState[(Double, MapFrame)]((0.0, MapFrame.Empty))
		                .customBy((mapData: TreeMap[Double, MapFrame], currentTime: UseRef[Double], currentFrame: UseState[(Double, MapFrame)]) =>
			                UseFrameDelta.H(delta => {
				                val updateTime: Callback = currentTime.mod(_ + delta)

				                println("amogus" + delta)
				                val frameSearchRes = mapData.maxBefore(currentTime.value).getOrElse((0.0, MapFrame.Empty))
				                val updateFrame: Callback = if (frameSearchRes != currentFrame.value) currentFrame.setState(frameSearchRes) else Callback.empty

				                updateTime >> updateFrame
			                })
		                )
		                .render((mapData: TreeMap[Double, MapFrame], currentTime: UseRef[Double], currentFrame: UseState[(Double, MapFrame)]) =>
			                <.div("among")
			                //BloqViewContainer.C(BloqViewContainer.Props(currentFrame.value._2, () => currentTime.value - currentFrame.value._1))
		                )
}

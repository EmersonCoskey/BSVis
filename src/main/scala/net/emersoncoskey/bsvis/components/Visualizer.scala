package net.emersoncoskey.bsvis.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.HookCtx
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.BloqViewContainer
import net.emersoncoskey.bsvis.data.beatsaber.MapFrame
import net.emersoncoskey.bsvis.hooks.{UseAnimationFrame, UseFrameDelta}

import scala.collection.immutable.TreeMap

object Visualizer {
	def C: ScalaFnComponent[TreeMap[Double, MapFrame], CtorType.Props] = Component

	val Component: ScalaFnComponent[TreeMap[Double, MapFrame], CtorType.Props] =
		ScalaFnComponent.withHooks[TreeMap[Double, MapFrame]]

		                .useRef(0.0)

		                .useState((0.0, MapFrame.Empty))

		                .customBy((mapData, currentTime, currentFrame) => UseFrameDelta.H(delta => Callback {
			                currentTime.value += delta
			                val lastFrameBeforeCurrentTime: (Double, MapFrame) = mapData.maxBefore(currentTime.value).getOrElse((0.0, MapFrame.Empty))
			                if (lastFrameBeforeCurrentTime != currentFrame.value) currentFrame.setState(lastFrameBeforeCurrentTime)
		                }))

		                .render(_ =>
			                <.div("among")
		                )
}
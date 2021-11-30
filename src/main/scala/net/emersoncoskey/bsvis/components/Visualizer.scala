package net.emersoncoskey.bsvis.components

import cats.effect.SyncIO
import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.Hooks._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.BloqViewContainer
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.hooks._
import net.emersoncoskey.bsvis.components.util.Implicits._

import scala.collection.immutable.TreeMap

object Visualizer {
	def C: ScalaFnComponent[TreeMap[Double, MapFrame], CtorType.Props] = Component

	val Component: ScalaFnComponent[TreeMap[Double, MapFrame], CtorType.Props] =
		ScalaFnComponent.withHooks[TreeMap[Double, MapFrame]]

		                .useRef[Double](0.0)

		                .useState[(Double, MapFrame)]((0.0, MapFrame.Empty))

		                .customBy((mapData     : TreeMap[Double, MapFrame],
		                           currentTime : UseRef[Double],
		                           currentFrame: UseState[(Double, MapFrame)]) =>
			                UseFrameDelta.H(delta => for {
				                _ <- currentTime.mod(_ + delta)

				                frameSearchRes = mapData.maxBefore(currentTime.value).getOrElse((0.0, MapFrame.Empty))
				                _ <- (frameSearchRes._1 > currentFrame.value._1) ?: currentFrame.setState(frameSearchRes)
			                } yield ())
		                )

		                .render((_           : TreeMap[Double, MapFrame],
		                         currentTime : UseRef[Double],
		                         currentFrame: UseState[(Double, MapFrame)]) => currentFrame.value match {
			                case (frameTime, frame) =>
				                <.div(
					                BloqViewContainer.C(BloqViewContainer.Props(frame, () => currentTime.value - frameTime)),
				                )
		                })
}

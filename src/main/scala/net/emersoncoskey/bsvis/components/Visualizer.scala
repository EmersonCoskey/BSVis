package net.emersoncoskey.bsvis.components

import cats.effect.{IO, SyncIO}
import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.Hooks._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.BloqViewContainer
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.hooks._
import cats.implicits._
import sttp.client3._
import sttp.client3.impl.cats.FetchCatsBackend

import scala.collection.immutable.TreeMap

object Visualizer {
	def C: ScalaFnComponent[TreeMap[Double, MapFrame[Bloq]], CtorType.Props] = Component

	val Component: ScalaFnComponent[TreeMap[Double, MapFrame[Bloq]], CtorType.Props] =
		ScalaFnComponent.withHooks[TreeMap[Double, MapFrame[Bloq]]]

		                .useRef[Double](0.0)

		                .useState[Double](0.0)
		                .useState[MapFrame[(Double, Bloq)]](MapFrame.Empty[(Double, Bloq)])

		                .customBy((mapData         : TreeMap[Double, MapFrame[Bloq]],
		                           currentTime     : UseRef[Double],
		                           currentFrameTime: UseState[Double],
		                           frameAccumulator: UseState[MapFrame[(Double, Bloq)]]) =>
			                UseFrameDelta.H(delta => for {
				                _ <- currentTime.mod(_ + delta)

				                frameSearchRes = mapData.maxBefore(currentTime.value).getOrElse((0.0, MapFrame.Empty[Bloq]))
				                newAccumulatorFrame = frameSearchRes._2.map((frameSearchRes._1, _))

				                _ <- (currentFrameTime.setState(frameSearchRes._1) >> frameAccumulator.modState(newAccumulatorFrame |+| _)).whenA(frameSearchRes._1 > currentFrameTime.value)
			                } yield ())
		                )

		                .render((_           : TreeMap[Double, MapFrame[Bloq]],
		                         currentTime : UseRef[Double],
		                         _           : UseState[Double],
		                         currentFrame: UseState[MapFrame[(Double, Bloq)]]) =>
			                <.div(
				                BloqViewContainer.C(BloqViewContainer.Props(currentFrame.value, () => currentTime.value))
			                )
		                )
}

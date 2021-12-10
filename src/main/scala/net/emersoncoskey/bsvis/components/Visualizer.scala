package net.emersoncoskey.bsvis.components

import cats.effect.{IO, SyncIO}
import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.Hooks._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.mapview.BloqViewContainer
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.hooks._
import cats.implicits._
import net.emersoncoskey.bsvis.components.mediacontrols.MediaControls
import net.emersoncoskey.bsvis.data.time.Seconds
import net.emersoncoskey.bsvis.data.time.Seconds.secondsOrdering
import sttp.client3._
import sttp.client3.impl.cats.FetchCatsBackend

import scala.collection.immutable.TreeMap

object Visualizer {
	def C: ScalaFnComponent[TreeMap[Seconds, MapFrame[Bloq]], CtorType.Props] = Component

	val Component: ScalaFnComponent[TreeMap[Seconds, MapFrame[Bloq]], CtorType.Props] =
		ScalaFnComponent.withHooks[TreeMap[Seconds, MapFrame[Bloq]]]

		                .useRef[Seconds](Seconds(0.0))

		                .useState[Seconds](Seconds(0.0))
		                .useState[MapFrame[(Seconds, Bloq)]](MapFrame.Empty[(Seconds, Bloq)])

		                .customBy((mapData         : TreeMap[Seconds, MapFrame[Bloq]],
		                           currentTime     : UseRef[Seconds],
		                           currentFrameTime: UseState[Seconds],
		                           frameAccumulator: UseState[MapFrame[(Seconds, Bloq)]]) =>
			                UseFrameDelta.H(delta => for {
				                _ <- currentTime.mod(_ + delta)

				                frameSearchRes = mapData.maxBefore(currentTime.value).getOrElse((Seconds(0.0), MapFrame.Empty[Bloq]))
				                newAccumulatorFrame = frameSearchRes._2.map((frameSearchRes._1, _))

				                _ <- currentFrameTime.setState(frameSearchRes._1).whenA(frameSearchRes._1 > currentFrameTime.value)
				                _ <- frameAccumulator.modState(newAccumulatorFrame |+| _).whenA(frameSearchRes._1 > currentFrameTime.value)
			                } yield ())
		                )

		                .render((mapData     : TreeMap[Seconds, MapFrame[Bloq]],
		                         currentTime : UseRef[Seconds],
		                         _           : UseState[Seconds],
		                         currentFrame: UseState[MapFrame[(Seconds, Bloq)]]) =>
			                <.div(
				                BloqViewContainer.C(BloqViewContainer.Props(currentFrame.value, () => currentTime.value)),

				                MediaControls.C(MediaControls.Props(mapData.last._1, () => currentTime.value, _ => SyncIO.unit, _ => SyncIO.unit)),
			                )
		                )
}

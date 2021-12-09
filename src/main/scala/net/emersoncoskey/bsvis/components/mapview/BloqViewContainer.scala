package net.emersoncoskey.bsvis.components.mapview

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.ScalaFn.Unmounted
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.data.time.Seconds

object BloqViewContainer {
	final case class Props(currentFrame: MapFrame[(Seconds, Bloq)], currentTime: () => Seconds)

	def C: ScalaFnComponent[Props, CtorType.Props] = Component

	def bloqViewElem(frameSection: Option[(Seconds, Bloq)], currentTime: () => Seconds): Unmounted[BloqView.Props] =
		BloqView.C(BloqView.Props(frameSection.map(_._2), () => currentTime() - frameSection.map(_._1).getOrElse(0.0))) //TODO: add math ops for number wrappers

	val Component: ScalaFnComponent[Props, CtorType.Props] =
		ScalaFnComponent.withHooks[Props]
		                .render { case Props(currentFrame, currentTime) =>
			                <.table(
				                <.tbody(
					                <.tr( // top row of notes
						                //TODO: eventually remove react fragments and labels
						                <.th(React.Fragment(bloqViewElem(currentFrame.t0, currentTime), "t0")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.t1, currentTime), "t1")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.t2, currentTime), "t2")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.t3, currentTime), "t3")),
					                ),
					                <.tr( // middle row of notes
						                <.th(React.Fragment(bloqViewElem(currentFrame.m0, currentTime), "m0")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.m1, currentTime), "m1")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.m2, currentTime), "m2")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.m3, currentTime), "m3")),
					                ),
					                <.tr( // bottom row of notes
						                <.th(React.Fragment(bloqViewElem(currentFrame.b0, currentTime), "b0")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.b1, currentTime), "b1")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.b2, currentTime), "b2")),
						                <.th(React.Fragment(bloqViewElem(currentFrame.b3, currentTime), "b3")),
					                )
				                )
			                )
		                }
}

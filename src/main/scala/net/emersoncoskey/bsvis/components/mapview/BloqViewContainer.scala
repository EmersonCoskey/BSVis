package net.emersoncoskey.bsvis.components.mapview

import japgolly.scalajs.react.{CtorType, _}
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.data.beatsaber._

object BloqViewContainer {
	final case class Props(currentFrame: MapFrame, timeSince: () => Double)

	def C: ScalaFnComponent[Props, CtorType.Props] = Component

	val Component: ScalaFnComponent[Props, CtorType.Props] =
		ScalaFnComponent.withHooks[Props]
		                .render { case Props(currentFrame, timeSince) =>
			                <.table(
				                <.tr( // top row of notes
					                <.th(BloqView.C(BloqView.Props(currentFrame.t0, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.t1, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.t2, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.t3, timeSince))),
				                ),
				                <.tr( // middle row of notes
					                <.th(BloqView.C(BloqView.Props(currentFrame.m0, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.m1, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.m2, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.m3, timeSince))),
				                ),
				                <.tr( // bottom row of notes
					                <.th(BloqView.C(BloqView.Props(currentFrame.b0, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.b1, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.b2, timeSince))),
					                <.th(BloqView.C(BloqView.Props(currentFrame.b3, timeSince))),
				                ),
			                )
		                }

}

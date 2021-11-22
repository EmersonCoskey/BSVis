package net.emersoncoskey.bsvis.components.mediacontrols

import japgolly.scalajs.react._
import japgolly.scalajs.react.hooks.Hooks.UseState
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.hooks.UseAnimationFrame


object SeekBar {
	final case class Props(maxTime    : Double,
	                       currentTime: () => Double, //should be a reference to getter on parent object probably (so time storage isn't distributed - that's bad)
	                       onSeek     : Double => Callback)

	def C: ScalaFnComponent[Props, CtorType.Props] = Component

	val Component: ScalaFnComponent[Props, CtorType.Props] =
		ScalaFnComponent.withHooks[Props]
		                .useState[Boolean](false)
		                .customBy((props: Props, isSeeking: UseState[Boolean]) => UseAnimationFrame.H(_ => Callback {
			                //TODO: update position of thingy along seekbar, clamped between 0 and max value
		                }))
		                .render((props: Props, isSeeking: UseState[Boolean]) =>
			                <.div(
				                <.div("currentTimeDisplay"), //TODO: REPLACE THESE WITH ACTUAL COMPONENTS
				                <.div("actualSeekbar"),
				                <.div("timeRemainingDisplay") //move to parent component?
			                )
		                )
}

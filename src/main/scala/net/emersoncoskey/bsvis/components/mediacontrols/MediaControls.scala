package net.emersoncoskey.bsvis.components.mediacontrols

import cats.effect.SyncIO
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.misc.ToggleButton
import net.emersoncoskey.bsvis.components.misc.ToggleButton._


object MediaControls {
	final case class Props(maxTime          : Double,
	                       currentTime      : () => Double,
	                       onSeek           : Double => SyncIO[Unit],
	                       onTogglePlayState: PlayState => SyncIO[Unit])

	sealed trait PlayState
	case object Playing extends PlayState
	case object Paused extends PlayState

	def C: ScalaFnComponent[Props, CtorType.Props] = Component

	val Component: ScalaFnComponent[Props, CtorType.Props] =
		ScalaFnComponent.withHooks[Props]
		                .useState[PlayState](Paused)
		                .render((props: Props, playState: Hooks.UseState[PlayState]) =>
			                <.div(
				                ToggleButton.C(ToggleButton.Props(
					                Secondary,
					                toggleState => {
						                val newPlayState = toggleState match {
							                case Primary => Playing
							                case Secondary => Paused
						                }
						                playState.setState(newPlayState) >> props.onTogglePlayState(newPlayState)
					                },
					                {
						                case Primary => ??? // TODO: SVG icons for button, refer to function above for what to map to
						                case Secondary => ???
					                }
				                )),

				                SeekBar.C(SeekBar.Props(props.maxTime, props.currentTime, props.onSeek)),
			                )
		                )

}

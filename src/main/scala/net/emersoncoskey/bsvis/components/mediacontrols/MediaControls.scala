package net.emersoncoskey.bsvis.components.mediacontrols

import cats.effect.SyncIO
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.misc.ToggleButton
import net.emersoncoskey.bsvis.components.misc.ToggleButton._
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.data.constants.BeatSaberSvgAssets
import net.emersoncoskey.bsvis.data.time.Seconds
import net.emersoncoskey.bsvis.hooks.UseAnimationFrame
import org.scalajs.dom.html
import net.emersoncoskey.bsvis.data.media._


object MediaControls {
	final case class Props(maxTime          : Seconds,
	                       currentTime      : () => Seconds,
	                       onSeek           : Seconds => SyncIO[Unit],
	                       onTogglePlayState: PlayState => SyncIO[Unit])

	def C: ScalaFnComponent[Props, CtorType.Props] = Component

	val Component: ScalaFnComponent[Props, CtorType.Props] =
		ScalaFnComponent.withHooks[Props]

		                .useState[PlayState](Paused)

		                .useRefToVdom[html.Div]
		                .customBy((props, _, ref) => UseAnimationFrame.H((t: Seconds) => ref.foreach(_.innerText = props.currentTime().time.floor.toString)))

		                .render((props: Props, playState: Hooks.UseState[PlayState], ref: Ref.ToVdom[html.Div]) =>
			                <.div(
				                ToggleButton.C(ToggleButton.Props(
					                Primary,
					                toggleState => {
						                val newPlayState = toggleState match {
							                case Primary => Playing
							                case Secondary => Paused
						                }
						                playState.setState(newPlayState) >> props.onTogglePlayState(newPlayState)
					                },
					                {
						                case Primary => <.img(
							                ^.width := "10em",
							                ^.height := "10em",
							                ^.src := BeatSaberSvgAssets.NoteAssetPath(Blue, D),
							                ^.transform := "rotate(270deg)"
						                ) // TODO: SVG icons for button, refer to function above for what to map to
						                case Secondary => <.img(
							                ^.width := "10em",
							                ^.height := "10em",
							                ^.src := BeatSaberSvgAssets.NoteAssetPath(Red, D),
							                ^.transform := "rotate(270deg)"
						                )
					                }
				                )),

				                SeekBar.C(SeekBar.Props(props.maxTime, props.currentTime, props.onSeek)),
				                <.div.withRef(ref)()
			                )
		                )

}

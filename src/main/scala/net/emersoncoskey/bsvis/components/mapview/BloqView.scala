package net.emersoncoskey.bsvis.components.mapview

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.hooks.UseAnimationFrame
import org.scalajs.dom.html

object BloqView {
	final case class Props(bloq: Option[Bloq], timeSince: () => Double)

	private def bloqOpacity(timeSince: Double): Double = Math.max(0, 15.7 * Math.pow(-timeSince, 3) + 1)

	def C: ScalaFnComponent[Props, CtorType.Props] = Component

	val Component: ScalaFnComponent[Props, CtorType.Props] =
		ScalaFnComponent.withHooks[Props]
		                
		                .useRefToVdom[html.Div]

		                .customBy((props, ref) => UseAnimationFrame.H(_ => {
			                val opacity = bloqOpacity(props.timeSince()).toString
			                ref.foreach(note => note.style.opacity = opacity)
		                }))

		                .render((props: Props, ref: Ref.ToVdom[html.Div]) => props match { case Props(bloq, _) =>
			                <.div(
				                <.div("background"), // (gray?) background behind note -> maybe put second in list, idk how html work

				                bloq.map {
					                case Note(color, dir) =>
						                <.div.withRef(ref)( //TODO: Replace with svg for note
							                ^.opacity := "1",
							                ^.width := "10em",
							                ^.height := "10em",
							                ^.backgroundColor := "red",
							                s"note with color $color and direction $dir",
					                    )
					                case Bomb =>
						                <.div.withRef(ref)( //TODO: Replace with svg for bomb
							                ^.opacity := "1",
							                ^.width := "10em",
							                ^.height := "10em",
							                ^.backgroundColor := "red",
							                "bomb",
						                )
				                }
			                )
		                })

}

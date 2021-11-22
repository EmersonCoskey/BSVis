package net.emersoncoskey.bsvis.components.util

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object ToggleButton {
	final case class Props(initialState       : State,
	                       onToggle           : State => Callback,
	                       switchableComponent: State => VdomNode)

	sealed trait State
	case object Primary extends State
	case object Secondary extends State

	def C: ScalaFnComponent[Props, CtorType.Props] = Component

	val Component: ScalaFnComponent[Props, CtorType.Props] =
		ScalaFnComponent.withHooks[Props]
		                .useStateBy[State](props => props.initialState)
		                .render((props: Props, toggleState: Hooks.UseState[State]) =>
			                <.button(
				                ^.onClick --> {
					                val newState = toggleState.value match {
						                case Primary => Secondary
						                case Secondary => Primary
					                }
					                toggleState.setState(newState) >>
					                  props.onToggle(newState)
				                },

				                props.switchableComponent(toggleState.value)
			                )
		                )
}

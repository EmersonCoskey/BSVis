package net.emersoncoskey.bsvis.components.util

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.util.containers.FnComponentContainer

object ToggleButton extends FnComponentContainer[ToggleButton.Props, CtorType.Props] {
	final case class Props(onToggle: State => Callback, primaryView: VdomNode, secondaryView: VdomNode)

	sealed trait State
	case object Primary extends State
	case object Secondary extends State

	protected override val component: ScalaFnComponent[Props, CtorType.Props] = ScalaFnComponent.withHooks[Props]
	  .useState[State](Primary)
	  .render((props, toggleState) =>
		  <.button(
			  ^.onClick --> {
				  val newState = toggleState.value match {
					  case Primary => Secondary
					  case Secondary => Primary
				  }
				  toggleState.setState(newState) >> props.onToggle(toggleState.value)
			  },

			  toggleState.value match {
				  case Primary => props.primaryView
				  case Secondary => props.secondaryView
			  }
		  )
	  )
}

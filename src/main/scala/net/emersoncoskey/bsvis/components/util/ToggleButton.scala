package net.emersoncoskey.bsvis.components.util

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.util.containers.FnComponentContainer

object ToggleButton {
	final case class Props(onToggle: State => Callback, primaryView: VdomNode, secondaryView: VdomNode)

	sealed trait State
	final case object Primary extends State
	final case object Secondary extends State

	val c: ScalaFnComponent[Props, CtorType.Props] = ScalaFnComponent.withHooks[Props]
	  .useState[State](Primary)
	  .render((props: Props, toggleState: Hooks.UseState[State]) =>
		  <.button(
			  ^.onClick --> {
				  val newState = toggleState.value match {
					  case Primary => Secondary
					  case Secondary => Primary
				  }
				  toggleState.setState(newState) >> props.onToggle(newState)
			  },
			  toggleState.value match {
				  case Primary => props.primaryView
				  case Secondary => props.secondaryView
			  }
		  )
	  )
}

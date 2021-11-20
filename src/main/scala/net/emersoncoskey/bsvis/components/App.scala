package net.emersoncoskey.bsvis.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.util.ToggleButton
import net.emersoncoskey.bsvis.components.util.containers.FnComponentContainer

object App extends FnComponentContainer[String, CtorType.Props] {
	override val component: ScalaFnComponent[String, CtorType.Props] = ScalaFnComponent.withHooks[String]
	  .render(text =>
		  ToggleButton.c(ToggleButton.Props(
			  _ => Callback(println("amogus")),
			  "amogus",
			  "ඞ",
		  ))
	  )
}
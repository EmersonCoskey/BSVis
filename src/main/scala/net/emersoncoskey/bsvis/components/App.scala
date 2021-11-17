package net.emersoncoskey.bsvis.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import net.emersoncoskey.bsvis.components.util.FnComponentContainer

object App extends FnComponentContainer[String, CtorType.Props] {
	override val Component = ScalaFnComponent.withHooks[String]
	  .render(<.div(_))
}

package net.emersoncoskey.bsvis.components.util.containers

import japgolly.scalajs.react.{CtorType, ScalaFnComponent}

trait FnComponentContainer[CT[-p, +u] <: CtorType[p, u]] {
	type Props

	protected val component: ScalaFnComponent[Props, CT]

	def c: ScalaFnComponent[Props, CT] = component
}

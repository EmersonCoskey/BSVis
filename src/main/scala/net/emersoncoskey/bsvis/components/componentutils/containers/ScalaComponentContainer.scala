package net.emersoncoskey.bsvis.components.componentutils.containers

import japgolly.scalajs.react.{CtorType, ScalaComponent}

trait ScalaComponentContainer[CT[-p, +u] <: CtorType[p, u]] {
	type Props
	type State
	type Backend

	protected val component: ScalaComponent[Props, State, Backend, CT]

	def c: ScalaComponent[Props, State, Backend, CT] = component
}

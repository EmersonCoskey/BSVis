package net.emersoncoskey.bsvis.components.util.containers

import japgolly.scalajs.react.{CtorType, ScalaComponent}

trait ScalaComponentContainer[P, S, B, CT[-p, +u] <: CtorType[p, u]] {
	protected val component: ScalaComponent[P, S, B, CT]

	def c: ScalaComponent[P, S, B, CT] = component
}

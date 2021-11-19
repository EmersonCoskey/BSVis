package net.emersoncoskey.bsvis.components.util

import japgolly.scalajs.react._

trait ScalaComponentContainer[P, S, B, CT[-p, +u] <: CtorType[p, u]] {
	protected val component: ScalaComponent[P, S, B, CT]

	def c: ScalaComponent[P, S, B, CT] = component
}
package net.emersoncoskey.bsvis.components.util

import japgolly.scalajs.react._

trait FnComponentContainer[P, CT[-p, +u] <: CtorType[p, u]] {
	protected val component: ScalaFnComponent[P, CT]

	def c: ScalaFnComponent[P, CT] = component
}

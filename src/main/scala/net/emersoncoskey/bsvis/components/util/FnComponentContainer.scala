package net.emersoncoskey.bsvis.components.util

import japgolly.scalajs.react._


trait FnComponentContainer[P, CT[-p, +u] <: CtorType[p, u]] {
	protected val Component: ScalaFnComponent[P, CT]

	def apply() = Component("amogus")
}

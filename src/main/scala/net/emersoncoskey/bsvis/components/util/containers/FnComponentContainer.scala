package net.emersoncoskey.bsvis.components.util.containers

import japgolly.scalajs.react.{CtorType, ScalaFnComponent}

trait FnComponentContainer[P, CT[-p, +u] <: CtorType[p, u]] {
	protected val component: ScalaFnComponent[P, CT]

	def c: ScalaFnComponent[P, CT] = component
}

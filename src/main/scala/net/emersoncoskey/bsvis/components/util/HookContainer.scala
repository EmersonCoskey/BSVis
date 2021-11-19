package net.emersoncoskey.bsvis.components.util

import japgolly.scalajs.react._

trait HookContainer[I, O] {
	protected val hook: CustomHook[I,O]

	def h: CustomHook[I,O] = hook
}

package net.emersoncoskey.bsvis.components.componentutils.containers

import japgolly.scalajs.react.CustomHook

trait HookContainer[I, O] {
	type Input
	type Output

	protected val hook: CustomHook[I, O]

	def h: CustomHook[I, O] = hook
}

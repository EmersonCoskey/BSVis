package net.emersoncoskey.bsvis.hooks

import japgolly.scalajs.react._
import org.scalajs.dom.window

object UseFrameDelta {
	def H: CustomHook[Double => Callback, Unit] = Hook

	val Hook: CustomHook[Double => Callback, Unit] = CustomHook[Double => Callback]
	  .useRef[Double](window.performance.now() / 1000)
	  .customBy((fn, currentTime) => UseAnimationFrame.H(newTime => {
		  val delta = newTime - currentTime.value
		  currentTime.value = newTime
		  fn(delta)
	  }))
	  .build
}

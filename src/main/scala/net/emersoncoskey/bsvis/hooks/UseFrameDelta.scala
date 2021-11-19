package net.emersoncoskey.bsvis.hooks

import japgolly.scalajs.react._
import net.emersoncoskey.bsvis.components.util.containers.HookContainer
import org.scalajs.dom.window

object UseFrameDelta extends HookContainer[Double => _, Unit] {
	protected override val hook: CustomHook[Function[Double, _], Unit] = CustomHook[Double => _]
	  .useRef(window.performance.now())
	  .customBy((fn, currentTime) => UseAnimationFrame.h(newTime => {
		  val delta = newTime - currentTime
		  currentTime.value = newTime
		  fn(delta)
	  }))
	  .build
}

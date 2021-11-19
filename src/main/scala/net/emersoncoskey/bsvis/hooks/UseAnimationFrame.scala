package net.emersoncoskey.bsvis.hooks

import japgolly.scalajs.react._
import net.emersoncoskey.bsvis.components.util.containers.HookContainer
import org.scalajs.dom._

object UseAnimationFrame extends HookContainer[Double => _, Unit] {
	override val hook: CustomHook[Double => _, Unit] = CustomHook[Double => _]
	  .useRef[Int](0)
	  .useEffectOnMountBy((fn, id) => CallbackTo {
		  id.value = window.requestAnimationFrame(fn)
		  Callback(window.cancelAnimationFrame(id.value))
	  })
	  .build
}
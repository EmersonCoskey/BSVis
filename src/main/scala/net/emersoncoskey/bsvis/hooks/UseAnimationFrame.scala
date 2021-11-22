package net.emersoncoskey.bsvis.hooks

import japgolly.scalajs.react._
import org.scalajs.dom._

object UseAnimationFrame {
	def H: CustomHook[Double => Callback, Unit] = Hook
	val Hook: CustomHook[Double => Callback, Unit] = CustomHook[Double => Callback]
	  .useRef[Int](0)
	  .useEffectOnMountBy((fn, id) => CallbackTo {
		  def animate(time: Double): Unit = {
			  val secs = time / 1000
			  fn(secs).runNow()
			  id.value = window.requestAnimationFrame(animate)
		  }
		  id.value = window.requestAnimationFrame(animate)
		  Callback(window.cancelAnimationFrame(id.value))
	  })
	  .build
}
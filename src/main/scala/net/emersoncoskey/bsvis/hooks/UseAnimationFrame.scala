package net.emersoncoskey.bsvis.hooks

import cats.effect.SyncIO
import japgolly.scalajs.react._
import org.scalajs.dom._

object UseAnimationFrame {
	def H: CustomHook[Double => SyncIO[Unit], Unit] = Hook

	val Hook: CustomHook[Double => SyncIO[Unit], Unit] = CustomHook[Double => SyncIO[Unit]]
	  .useRef[Int](0)
	  .useEffectBy((fn, id) => SyncIO {
		  def animate(time: Double): Unit = {
			  val secs = time / 1000
			  fn(secs).unsafeRunSync()
			  id.value = window.requestAnimationFrame(animate)
		  }

		  id.value = window.requestAnimationFrame(animate)
		  SyncIO(window.cancelAnimationFrame(id.value))
	  })
	  .build
}
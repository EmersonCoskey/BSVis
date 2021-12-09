package net.emersoncoskey.bsvis.hooks

import cats.effect.SyncIO
import japgolly.scalajs.react._
import net.emersoncoskey.bsvis.data.time.Seconds
import org.scalajs.dom._

object UseAnimationFrame {
	def H: CustomHook[Seconds => SyncIO[Unit], Unit] = Hook

	val Hook: CustomHook[Seconds => SyncIO[Unit], Unit] = CustomHook[Seconds => SyncIO[Unit]]
	  .useRef[Int](0)
	  .useEffectBy((fn, id) => SyncIO {
		  def animate(time: Double): Unit = {
			  val secs = Seconds(time / 1000)
			  fn(secs).unsafeRunSync()
			  id.value = window.requestAnimationFrame(animate)
		  }

		  id.value = window.requestAnimationFrame(animate)
		  SyncIO(window.cancelAnimationFrame(id.value))
	  })
	  .build
}
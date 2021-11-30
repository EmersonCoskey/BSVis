package net.emersoncoskey.bsvis.hooks

import cats.effect.SyncIO
import japgolly.scalajs.react._
import org.scalajs.dom.window

object UseFrameDelta {
	def H: CustomHook[Double => SyncIO[Unit], Unit] = Hook

	val Hook: CustomHook[Double => SyncIO[Unit], Unit] = CustomHook[Double => SyncIO[Unit]]
	  .useRef[Double](window.performance.now() / 1000)
	  .customBy((fn, currentTime) => UseAnimationFrame.H(newTime => for {
		  _ <- fn(newTime - currentTime.value)
		  _ <- currentTime.set(newTime)
	  } yield ()))
	  .build
}

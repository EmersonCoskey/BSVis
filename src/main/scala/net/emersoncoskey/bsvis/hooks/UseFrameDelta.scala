package net.emersoncoskey.bsvis.hooks

import cats.effect.SyncIO
import japgolly.scalajs.react._
import org.scalajs.dom.window
import net.emersoncoskey.bsvis.data.time.Seconds

object UseFrameDelta {
	def H: CustomHook[Seconds => SyncIO[Unit], Unit] = Hook

	val Hook: CustomHook[Seconds => SyncIO[Unit], Unit] = CustomHook[Seconds => SyncIO[Unit]]
	  .useRef[Seconds](Seconds(window.performance.now() / 1000))
	  .customBy((fn, currentTime) => UseAnimationFrame.H(newTime => for {
		  _ <- SyncIO(println(newTime - currentTime.value))
		  _ <- fn(newTime - currentTime.value)
		  _ <- currentTime.set(newTime)
	  } yield ()))
	  .build
}

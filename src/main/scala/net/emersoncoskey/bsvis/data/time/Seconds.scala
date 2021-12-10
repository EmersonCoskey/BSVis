package net.emersoncoskey.bsvis.data.time

import io.circe.{Decoder, HCursor}

case class Seconds(time: Double) extends AnyVal {
	def map(op: Double => Double): Seconds = Seconds(op(time))

	def *(x: Seconds): Seconds = Seconds(time * x.time)
	def /(x: Seconds): Seconds = Seconds(time / x.time)
	def %(x: Seconds): Seconds = Seconds(time % x.time)

	def +(x: Seconds): Seconds = Seconds(time + x.time)
	def -(x: Seconds): Seconds = Seconds(time - x.time)

	def ==(x: Seconds): Boolean = time == x.time
	def !=(x: Seconds): Boolean = time != x.time

	def <(x: Seconds): Boolean = time < x.time
	def <=(x: Seconds): Boolean = time <= x.time
	def >(x: Seconds): Boolean = time > x.time
	def >=(x: Seconds): Boolean = time >= x.time

	def unary_+ : Seconds = this
	def unary_- : Seconds = Seconds(-time)
}

object Seconds {
	implicit val decodeSeconds: Decoder[Seconds] = (c: HCursor) => for (time <- c.as[Double]) yield Seconds(time)

	implicit val secondsOrdering: Ordering[Seconds] = (x: Seconds, y: Seconds) => x.time.compare(y.time)
}

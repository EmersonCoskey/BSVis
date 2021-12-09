package net.emersoncoskey.bsvis.data.time

import io.circe.Decoder.Result
import io.circe.{Decoder, HCursor}

case class Beats(time: Double) extends AnyVal {
	def toSeconds(bpm: Double, offset: Seconds, shuffle: Beats, shufflePeriod: Beats): Seconds = {
		val shufflePosition: Double = time / shufflePeriod.time % 2
		val shuffledBeats  : Double = if (1.0 <= shufflePosition && shufflePosition < 2.0) time + shuffle.time else time
		val seconds        : Double = (60.0 / bpm) * shuffledBeats + offset.time //TODO: make sure offset is actually applied to notes
		Seconds(seconds)
	}

	def map(op: Double => Double): Beats = Beats(op(time))
}

object Beats {
	implicit val decodeBeats: Decoder[Beats] = (c: HCursor) => for (time <- c.as[Double]) yield Beats(time)

	implicit val beatsOrdering: Ordering[Beats] = (x: Beats, y: Beats) => x.time.compare(y.time)
}

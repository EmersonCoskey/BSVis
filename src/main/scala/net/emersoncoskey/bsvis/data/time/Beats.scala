package net.emersoncoskey.bsvis.data.time

case class Beats(time: Double) extends AnyVal {
	def toSeconds(bpm: Double, offset: Seconds, shuffle: Beats, shufflePeriod: Beats): Seconds = {
		val shufflePosition: Double = time / shufflePeriod.time % 2
		val shuffledBeats  : Double = if (1.0 <= shufflePosition && shufflePosition < 2.0) time + shuffle.time else time
		val seconds        : Double = (60.0 / bpm) * shuffledBeats + offset.time //TODO: make sure offset is actually applied to notes
		Seconds(seconds)
	}
}

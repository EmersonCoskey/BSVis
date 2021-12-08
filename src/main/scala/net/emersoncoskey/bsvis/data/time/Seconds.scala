package net.emersoncoskey.bsvis.data.time

import io.circe.{Decoder, HCursor}

case class Seconds(time: Double) extends AnyVal

object Seconds {
	implicit val decodeBeats: Decoder[Seconds] = (c: HCursor) => for (time <- c.as[Double]) yield Seconds(time)
}

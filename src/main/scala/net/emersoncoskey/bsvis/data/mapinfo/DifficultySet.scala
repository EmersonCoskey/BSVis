package net.emersoncoskey.bsvis.data.mapinfo

import io.circe.{Decoder, HCursor}

case class DifficultySet(characteristicName: String, difficulties: List[DifficultySummary])

object DifficultySet {
	implicit val decodeDifficultySet: Decoder[DifficultySet] = (c: HCursor) => for {
		characteristicName <- c.downField("_beatmapCharacteristicName").as[String]
		difficulties <- c.downField("_difficultyBeatmaps").as[List[DifficultySummary]]
	} yield DifficultySet(characteristicName, difficulties)
}

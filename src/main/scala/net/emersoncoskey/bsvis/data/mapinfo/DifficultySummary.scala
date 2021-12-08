package net.emersoncoskey.bsvis.data.mapinfo

import io.circe.{Decoder, HCursor}

case class DifficultySummary(difficulty     : String,
                             difficultyRank : Int,
                             filename       : String,
                             noteJumpSpeed  : Double,
                             noteSpawnOffset: Double)

object DifficultySummary {
	implicit val decodeDifficultySummary: Decoder[DifficultySummary] = (c: HCursor) => for {
		difficulty <- c.downField("_difficulty").as[String]
		difficultyRank <- c.downField("_difficultyRank").as[Int]
		filename <- c.downField("_filename").as[String]
		noteJumpSpeed <- c.downField("_noteJumpMovementSpeed").as[Double]
		noteSpawnOffset <- c.downField("_noteJumpStartBeatOffset").as[Double]
	} yield DifficultySummary(difficulty,
		                      difficultyRank,
		                      filename,
		                      noteJumpSpeed,
		                      noteSpawnOffset)
}

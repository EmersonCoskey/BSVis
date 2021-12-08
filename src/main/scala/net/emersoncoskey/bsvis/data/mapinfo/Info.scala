package net.emersoncoskey.bsvis.data.mapinfo

import net.emersoncoskey.bsvis.data.time._
import io.circe._

case class Info(version                     : String,
                songName                    : String,
                songSubName                 : String,
                songAuthorName              : String,
                levelAuthorName             : String,
                beatsPerMinute              : Double,
                shuffle                     : Beats,
                shufflePeriod               : Beats,
                previewStartTime            : Seconds,
                previewDuration             : Seconds,
                songFilename                : String,
                coverImageFilename          : String,
                environmentName             : String,
                allDirectionsEnvironmentName: String,
                songTimeOffset              : Seconds,
                /*_customData                  : Any,*/
                difficultySets              : List[DifficultySet])

object Info {
	implicit val decodeInfo: Decoder[Info] = (c: HCursor) => for {
		version <- c.downField("_version").as[String]
		songName <- c.downField("_songName").as[String]
		songSubName <- c.downField("_songSubName").as[String]
		songAuthorName <- c.downField("_songAuthorName").as[String]
		levelAuthorName <- c.downField("_levelAuthorName").as[String]
		beatsPerMinute <- c.downField("_beatsPerMinute").as[Double]
		shuffle <- c.downField("_shuffle").as[Beats]
		shufflePeriod <- c.downField("_shufflePeriod").as[Beats]
		previewStartTime <- c.downField("_previewStartTime").as[Seconds]
		previewDuration <- c.downField("_previewDuration").as[Seconds]
		songFileName <- c.downField("_songFileName").as[String]
		coverImageFilename <- c.downField("_coverImageFilename").as[String]
		environmentName <- c.downField("_environmentName").as[String]
		allDirectionsEnvironmentName <- c.downField("_allDirectionsEnvironmentName").as[String]
		songTimeOffset <- c.downField("_songTimeOffset").as[Seconds]
		difficultySets <- c.downField("_difficultyBeatmapSets").as[List[DifficultySet]]
	} yield Info(version,
				 songName,
				 songSubName,
				 songAuthorName,
				 levelAuthorName,
				 beatsPerMinute,
				 shuffle,
				 shufflePeriod,
				 previewStartTime,
				 previewDuration,
				 songFileName,
				 coverImageFilename,
				 environmentName,
				 allDirectionsEnvironmentName,
				 songTimeOffset,
				 difficultySets)
}



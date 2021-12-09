package net.emersoncoskey.bsvis.data.mapinfo

import io.circe._
import net.emersoncoskey.bsvis.data.beatsaber._
import net.emersoncoskey.bsvis.data.time._
import cats.implicits._

import scala.collection.immutable.TreeMap

case class Difficulty(version: String,
                      notes  : TreeMap[Seconds, MapFrame[Bloq]])

object Difficulty {
	private case class NoteJsonHelper(time: Beats,
	                                  lineIndex: Int,
	                                  lineLayer: Int,
	                                  `type`: Int,
	                                  cutDirection: Int) {
		def toMapFrame: MapFrame[Bloq] = {
			val noteDirection: NoteDirection = cutDirection match {
				case 0 => U
				case 1 => D
				case 2 => L
				case 3 => R
				case 4 => UL
				case 5 => UR
				case 6 => DL
				case 7 => DR
				case 8 => Dot

				case _ => Dot
			}

			val bloq: Bloq = `type` match {
				case 0 => Note(Red, noteDirection)
				case 1 => Note(Blue, noteDirection)
				case 3 => Bomb

				case _ => Bomb //2 is unused
			}

			(lineIndex, lineLayer) match { //line index in descending order because beatsaber counts from bottom left corner instead or top left :)
				case (0, 2) => MapFrame(Some(bloq), None, None, None, None, None, None, None, None, None, None, None)
				case (1, 2) => MapFrame(None, Some(bloq), None, None, None, None, None, None, None, None, None, None)
				case (2, 2) => MapFrame(None, None, Some(bloq), None, None, None, None, None, None, None, None, None)
				case (3, 2) => MapFrame(None, None, None, Some(bloq), None, None, None, None, None, None, None, None)
				case (0, 1) => MapFrame(None, None, None, None, Some(bloq), None, None, None, None, None, None, None)
				case (1, 1) => MapFrame(None, None, None, None, None, Some(bloq), None, None, None, None, None, None)
				case (2, 1) => MapFrame(None, None, None, None, None, None, Some(bloq), None, None, None, None, None)
				case (3, 1) => MapFrame(None, None, None, None, None, None, None, Some(bloq), None, None, None, None)
				case (0, 0) => MapFrame(None, None, None, None, None, None, None, None, Some(bloq), None, None, None)
				case (1, 0) => MapFrame(None, None, None, None, None, None, None, None, None, Some(bloq), None, None)
				case (2, 0) => MapFrame(None, None, None, None, None, None, None, None, None, None, Some(bloq), None)
				case (3, 0) => MapFrame(None, None, None, None, None, None, None, None, None, None, None, Some(bloq))

				case _ => MapFrame(None, None, None, None, None, None, None, None, None, None, None, None)
			}
		}
	}

	implicit val decodeDifficulty: Decoder[Difficulty] = (c: HCursor) => for {
		version <- c.downField("_version").as[String]
		notesList <- c.downField("_notes").as[List[NoteJsonHelper]]
	} yield Difficulty(version, TreeMap(
		notesList.groupBy(_.time)
		         .map { case (k, v) => (k, v.map(_.toMapFrame).combineAll) }
		         .toSeq:_*
	))

	private implicit val decodeNoteJsonHelper: Decoder[NoteJsonHelper] = (c: HCursor) => for {
		time <- c.downField("_time").as[Beats]
		lineIndex <- c.downField("_lineIndex").as[Int]
		lineLayer <- c.downField("_lineLayer").as[Int]
		_type <- c.downField("_type").as[Int]
		cutDirection <- c.downField("_cutDirection").as[Int]
	} yield NoteJsonHelper(time, lineIndex, lineLayer, _type, cutDirection)
}

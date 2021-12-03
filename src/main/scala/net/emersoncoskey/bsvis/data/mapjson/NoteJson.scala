package net.emersoncoskey.bsvis.data.mapjson

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import net.emersoncoskey.bsvis.data.beatsaber._

case class NoteJson(_time        : Double,
                    _lineIndex   : Int,
                    _lineLayer   : Int,
                    _type        : Int,
                    _cutDirection: Int,
                    /*_customData  : Any*/) {

	def toMapFrame: MapFrame = {
		val noteDirection: NoteDirection = _cutDirection match {
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

		val bloq: Bloq = _type match {
			case 0 => Note(Blue, noteDirection)
			case 1 => Note(Red, noteDirection)
			case 3 => Bomb

			case _ => Bomb //2 is unused
		}

		(_lineIndex, _lineLayer) match {
			case (0, 0) => MapFrame(Some(bloq), None, None, None, None, None, None, None, None, None, None, None)
			case (1, 0) => MapFrame(None, Some(bloq), None, None, None, None, None, None, None, None, None, None)
			case (2, 0) => MapFrame(None, None, Some(bloq), None, None, None, None, None, None, None, None, None)
			case (3, 0) => MapFrame(None, None, None, Some(bloq), None, None, None, None, None, None, None, None)
			case (0, 1) => MapFrame(None, None, None, None, Some(bloq), None, None, None, None, None, None, None)
			case (1, 1) => MapFrame(None, None, None, None, None, Some(bloq), None, None, None, None, None, None)
			case (2, 1) => MapFrame(None, None, None, None, None, None, Some(bloq), None, None, None, None, None)
			case (3, 1) => MapFrame(None, None, None, None, None, None, None, Some(bloq), None, None, None, None)
			case (0, 2) => MapFrame(None, None, None, None, None, None, None, None, Some(bloq), None, None, None)
			case (1, 2) => MapFrame(None, None, None, None, None, None, None, None, None, Some(bloq), None, None)
			case (2, 2) => MapFrame(None, None, None, None, None, None, None, None, None, None, Some(bloq), None)
			case (3, 2) => MapFrame(None, None, None, None, None, None, None, None, None, None, None, Some(bloq))
		}
	}
}

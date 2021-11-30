package net.emersoncoskey.bsvis.data.constants

import net.emersoncoskey.bsvis.data.beatsaber._

object BeatSaberSvgAssets {
	val BeatSaberAssetsRoot: String = "https://laugexd.github.io/beat-saber-assets/icons/notes/"

	val BlueDirectional: String = "blue-directional.svg"
	val BlueNonDirectional: String = "blue-nondirectional.svg"

	val RedDirectional: String = "red-directional.svg"
	val RedNonDirectional: String = "red-nondirectional.svg"

	def NoteAssetPath(color: NoteColor, dir: NoteDirection): String = BeatSaberAssetsRoot + (color match {
		case Blue => dir match {
			case Dot => BlueNonDirectional
			case _ => BlueDirectional
		}
		case Red => dir match {
			case Dot => RedNonDirectional
			case _ => RedDirectional
		}
	})
}

package net.emersoncoskey.bsvis.data.beatsaber

sealed trait NoteDirection

case object Dot extends NoteDirection

case object U extends NoteDirection
case object D extends NoteDirection
case object L extends NoteDirection
case object R extends NoteDirection

case object UL extends NoteDirection
case object UR extends NoteDirection
case object DL extends NoteDirection
case object DR extends NoteDirection
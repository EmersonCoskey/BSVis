package net.emersoncoskey.bsvis.data.beatsaber

sealed trait Bloq

case class Note(color: NoteColor, direction: NoteDirection) extends Bloq
case object Bomb extends Bloq
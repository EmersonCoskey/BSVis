package net.emersoncoskey.bsvis.data.media

sealed trait PlayState

case object Playing extends PlayState

case object Paused extends PlayState

package net.emersoncoskey.bsvis.components.util

import cats.effect.{IO, SyncIO}

object Implicits {
	implicit class IOExtension(io: IO[Unit]) {
		def ?:(condition: Boolean): IO[Unit] = if (condition) io else IO.unit
	}

	implicit class SyncIOExtension(syncIO: SyncIO[Unit]) {
		def ?:(condition: Boolean): SyncIO[Unit] = if (condition) syncIO else SyncIO.unit
	}
}

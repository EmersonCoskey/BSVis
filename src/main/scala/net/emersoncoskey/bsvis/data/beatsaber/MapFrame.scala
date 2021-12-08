package net.emersoncoskey.bsvis.data.beatsaber

import cats.{Functor, Monoid}
import cats.implicits._

case class MapFrame[A](t0: Option[A], t1: Option[A], t2: Option[A], t3: Option[A],
                       m0: Option[A], m1: Option[A], m2: Option[A], m3: Option[A],
                       b0: Option[A], b1: Option[A], b2: Option[A], b3: Option[A])

object MapFrame {
	def Empty[A]: MapFrame[A] = MapFrame(None, None, None, None,
		                                 None, None, None, None,
		                                 None, None, None, None)

	implicit val MapFrameFunctor: Functor[MapFrame] = new Functor[MapFrame] {
		override def map[A, B](fa: MapFrame[A])(f: A => B): MapFrame[B] =
			MapFrame(fa.t0.map(f), fa.t1.map(f), fa.t1.map(f), fa.t3.map(f),
				     fa.m0.map(f), fa.m1.map(f), fa.m2.map(f), fa.m3.map(f),
				     fa.b0.map(f), fa.b1.map(f), fa.b2.map(f), fa.b3.map(f))
	}

	implicit def MapFrameMonoid[A]: Monoid[MapFrame[A]] = new Monoid[MapFrame[A]] {
		override def empty: MapFrame[A] = MapFrame.Empty[A]

		override def combine(x: MapFrame[A], y: MapFrame[A]): MapFrame[A] =
			MapFrame(x.t0 <+> y.t0, x.t1 <+> y.t1, x.t2 <+> y.t2, x.t3 <+> y.t3,
				     x.m0 <+> y.m0, x.m1 <+> y.m1, x.m2 <+> y.m2, x.m3 <+> y.m3,
				     x.b0 <+> y.b0, x.b1 <+> y.b1, x.b2 <+> y.b2, x.b3 <+> y.b3)

	}
}

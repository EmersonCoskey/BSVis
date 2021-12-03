package net.emersoncoskey.bsvis.data.beatsaber

import cats.Monoid

case class MapFrame(t0: Option[Bloq], t1: Option[Bloq], t2: Option[Bloq], t3: Option[Bloq],
                    m0: Option[Bloq], m1: Option[Bloq], m2: Option[Bloq], m3: Option[Bloq],
                    b0: Option[Bloq], b1: Option[Bloq], b2: Option[Bloq], b3: Option[Bloq])/* {
	def overlay(other: MapFrame): MapFrame =
		MapFrame(other.t0.orElse(t0), other.t1.orElse(t1), other.t2.orElse(t2), other.t3.orElse(t3),
			     other.m0.orElse(m0), other.m1.orElse(m1), other.m2.orElse(m2), other.m3.orElse(m3),
			     other.b0.orElse(b0), other.b1.orElse(b1), other.b2.orElse(b2), other.b3.orElse(b3))
}*/

/*case class MapFrameTimed(t0: Option[(Bloq, Double)], t1: Option[(Bloq, Double)], t2: Option[(Bloq, Double)], t3: Option[(Bloq, Double)],
                         m0: Option[(Bloq, Double)], m1: Option[(Bloq, Double)], m2: Option[(Bloq, Double)], m3: Option[(Bloq, Double)],
                         b0: Option[(Bloq, Double)], b1: Option[(Bloq, Double)], b2: Option[(Bloq, Double)], b3: Option[(Bloq, Double)]) {
	def overlay(other: MapFrameTimed): MapFrameTimed =
		MapFrameTimed(other.t0.orElse(t0), other.t1.orElse(t1), other.t2.orElse(t2), other.t3.orElse(t3),
			          other.m0.orElse(m0), other.m1.orElse(m1), other.m2.orElse(m2), other.m3.orElse(m3),
			          other.b0.orElse(b0), other.b1.orElse(b1), other.b2.orElse(b2), other.b3.orElse(b3))
}*/

object MapFrame {
	val Empty: MapFrame = MapFrame(None, None, None, None,
		                           None, None, None, None,
		                           None, None, None, None)

	implicit val MapFrameMonoid: Monoid[MapFrame] = new Monoid[MapFrame] {
		override def empty: MapFrame = MapFrame.Empty

		override def combine(x: MapFrame, y: MapFrame): MapFrame =
			MapFrame(y.t0.orElse(x.t0), y.t1.orElse(x.t1), y.t2.orElse(x.t2), y.t3.orElse(x.t3),
				     y.m0.orElse(x.m0), y.m1.orElse(x.m1), y.m2.orElse(x.m2), y.m3.orElse(x.m3),
				     y.b0.orElse(x.b0), y.b1.orElse(x.b1), y.b2.orElse(x.b2), y.b3.orElse(x.b3))
	}
}

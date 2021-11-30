package net.emersoncoskey.bsvis

import net.emersoncoskey.bsvis.components.App
import org.scalajs.dom.document

object BSVis {
	def main(args: Array[String]): Unit = App.C().renderIntoDOM(document.getElementById("root"))
}

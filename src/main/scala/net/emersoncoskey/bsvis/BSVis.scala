package net.emersoncoskey.bsvis

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.App
import org.scalajs.dom.document

object BSVis {
	def main(args: Array[String]): Unit = {
		App.Component("amogus").renderIntoDOM(document.body)
	}
}

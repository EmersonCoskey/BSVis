package net.emersoncoskey.bsvis

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.emersoncoskey.bsvis.components.App
import org.scalajs.dom.document

object BSVis {
	val Component = ScalaComponent.builder[String]
	  .render_P(name =>
		  <.button(
			  ^.onClick --> Callback(println(name)),
			  name,
		  )
	  )
	  .build

	def main(args: Array[String]): Unit = {
		App()("Amogus").renderIntoDOM(document.body)
	}
}

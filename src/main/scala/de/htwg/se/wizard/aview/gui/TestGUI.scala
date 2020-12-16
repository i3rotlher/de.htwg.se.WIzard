package de.htwg.se.wizard.aview.gui

import scala.swing.{BoxPanel, _}
import scala.swing.event.ButtonClicked

object TestGUI extends Frame{

  title = "Test"

  val label = "Test Label"

  val box_panel = new BoxPanel(Orientation.Vertical) {

    val button3 = new Button("3 Players") {
      name = "3"
    }
    val button4 = new Button("4 Players") {
      name = "4"
    }
    val button5 = new Button("5 Players"){
      name = "5"
    }
    val button6 = new Button("6 Players"){
      name = "6"
    }

    contents += button3
    contents += button4
    contents += button5
    contents += button6
    border = Swing.EmptyBorder(30, 30, 10, 30)
  }

  contents = box_panel

  listenTo(box_panel.button3)
  listenTo(box_panel.button4)
  listenTo(box_panel.button5)
  listenTo(box_panel.button6)

  reactions += {
    case ButtonClicked(b) => println(b.text + " has been clicked!")
  }

  visible = true

  def main (args: Array[String] ): Unit = {

  }

}



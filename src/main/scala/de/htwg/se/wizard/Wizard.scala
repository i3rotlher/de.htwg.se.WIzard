package de.htwg.se.wizard
import de.htwg.se.wizard.control._
import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.aview.gui.SwingGUI
import de.htwg.se.wizard.model._

object Wizard {

  val controller = new Controller(Gamestate())
  val tui = new TUI(controller)
  val gui = new SwingGUI(controller)

  def main(args: Array[String]): Unit = {
    controller.publish(new game_started)
    do {
      val input = scala.io.StdIn.readLine()
      tui.processInput(input)
    } while (!tui.state.isInstanceOf[game_over])
  }

}


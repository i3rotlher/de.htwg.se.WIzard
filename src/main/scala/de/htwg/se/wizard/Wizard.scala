package de.htwg.se.wizard

import de.htwg.se.wizard.control.{Controller, State}
import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.model._

object Wizard {

  val controller = new Controller(Gamestate())
  val tui = new TUI(controller)

  def main(args: Array[String]): Unit = {
    controller.notify_Observer(State.game_started)
    do {
      val input = scala.io.StdIn.readLine()
      tui.processInput(input)
    } while (tui.state != State.game_over)
  }

}


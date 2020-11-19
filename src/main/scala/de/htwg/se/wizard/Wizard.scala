package de.htwg.se.wizard

import de.htwg.se.wizard.control.Controller
import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.model._

object Wizard {

  val controller = new Controller(Gamestate())
  val tui = new TUI(controller)

  def main(args: Array[String]): Unit = {
    println("Willkommen zu Wizzard\n\n")
    controller.notify_Observer("game_started")

    for (round_number <- 0 until number_of_rounds(controller.player_amount())) {
      controller.play_round(round_number)
    }
    println("Wizard is over!")
  }

  def number_of_rounds(player_amount: Int): Int = {
    60 / player_amount
  }

}


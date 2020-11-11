package de.htwg.se.wizard.*

import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.model.{Gamestate, Round}

object Wizard {
  def main(args: Array[String]): Unit = {
    val tui = TUI()
    var game = Gamestate()
    //spieler anlegen
    game = tui.createPlayers()
    //do while (spiel nicht vorbei)

    do {
      var guessed = List(0, 0)
      //for
        //anzahl stiche abfragen

      val made = List(2, 2)
      //for spieleranzahl
        //alle ründhcen spielen -> anzahl gemachter stiche

      var round = Round(guessed)
      round = round.madeTricks(made)
      game.round_finished(round)
    } while (game.isOver())
    print("Wizard is over!")
  }
}

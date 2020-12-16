package de.htwg.se.wizard.aview.gui

import de.htwg.se.wizard.model.Gamestate
import scala.swing._

class Table_panel(gamestate: Gamestate) extends FlowPanel {
  var game_table = gamestate.game_table

  //preferredSize = new Dimension(1200, 325)

  var names = Array.fill(gamestate.players.size){""}
  for ((player,x) <- gamestate.players.zipWithIndex) {
    names(x) = player.name
  }

  var rounds: Array[Array[Any]] = Array.fill(gamestate.players.size,60/gamestate.players.size){"   "}
  for ((round, x) <- game_table.zipWithIndex) {
    for (y <- 0 to gamestate.players.size) {
       rounds(x)(y) = round.results(y)+ "  | " + round.guessed_tricks(y)
    }
  }
  val table: Table = new Table(rounds ,names.toSeq)

  val table_scroll_pane = new ScrollPane(table) {
    contents = table
  }
  contents+=table_scroll_pane
}

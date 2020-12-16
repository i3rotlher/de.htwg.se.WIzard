3+4


import de.htwg.se.wizard.model.{Cards, Gamestate, Player, Round}
import org.graalvm.compiler.code.DataSection.RawData

import scala.swing.{BoxPanel, Dimension, Frame, Orientation, ScrollPane, Table}


class Table_panel(gamestate: Gamestate) extends BoxPanel(Orientation.Vertical) {
  var game_table = gamestate.game_table

  preferredSize = new Dimension(gamestate.players.size*50, (60/gamestate.players.size*18.5).toInt)
  enabled = false


  var names = Array.fill(gamestate.players.size){""}
  for ((player,x) <- gamestate.players.zipWithIndex) {
    names(x) = player.name
  }

  var rounds: Array[Array[Any]] = Array.fill(60/gamestate.players.size,gamestate.players.size){"   "}
  for ((round, x) <- game_table.zipWithIndex) {
    for (y <- gamestate.players.indices) {
      rounds(x)(y) = round.results(y)+ "  | " + round.guessed_tricks(y)
    }
  }

  val table: Table = new Table(rounds ,names.toSeq) {
    enabled = false
  }

  val table_scroll_pane = new ScrollPane(table) {
    contents = table
    enabled = false
    focusable = false
  }
  contents+=table_scroll_pane
}

val frame = new Frame

frame.contents = new Table_panel(Gamestate(players = List(Player("1", List(Cards.all_cards(0))),
  Player("2", List(Cards.all_cards(0))),
  Player("3", List(Cards.all_cards(0))),
  Player("4", List(Cards.all_cards(0))),
  Player("5", List(Cards.all_cards(0))),
  Player("6", List(Cards.all_cards(0)))),
  game_table = List(Round(List(0,0,1,1,1,2), List(20,20,30,-10,-10,-10)), Round(List(0,1,2,3,4,5), List(20,20,30,120,300,-80)))))


frame.visible = true
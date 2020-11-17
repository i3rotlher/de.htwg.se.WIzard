package de.htwg.se.wizard

import de.htwg.se.wizard.control.Controller
import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.model._

object Wizard {

    var game = Gamestate()
    val controller = de.htwg.se.wizard.control.Controller(game)
    val tui = TUI(controller)

  def main(args: Array[String]): Unit = {
    println("Willkommen zu Wizzard\n\n")
    game = tui.createPlayers()

    //Anfang For Schleife des Spiels
    for (round_number <- 1 to 60 / game.players.size) { //
      play_round(round_number)
//      tui.begin_round(round_number)
//
//      //Karten generieren
//      game = game.generate_Hands(round_number)
//
//      //setzte trumpcard auf den narren für die letzte runde
//      var trumpcard = Cards.all_cards(0)
//      //erzeuge nur Trumpcard runde 1 bis vorletzte Runde (übrige karte nach hands) und zeige Sie
//      if(round_number != 60/game.players.size){
//        trumpcard = game.get_Trump_card(game.players)
//        tui.show_Trumpcard(trumpcard)
//      }
//
//      //anzahl stiche abfragen
//      game = game.round_start(tui.get_guesses(game, round_number))
//
//      //gewonnene stiche zählen
//      val made = Array.fill(game.players.size) {0}
//
//      //ründchenstarter (am anfang round_number-1, danach der winner)
//      var mini_starter = round_number - 1
//      var mini_cards = List[Card]()
//
//      //ründchenanzahl = counter
//      for (_ <- 0 until round_number) { //alle mini ründchen
//        var serve_card = Cards.all_cards(0)
//        for (mini_round_player <- game.players.indices) { //jeder spieler in einem miniründchen
//          var not_playable = true
//          while (not_playable) {
//            val current_player = (mini_starter + mini_round_player) % game.players.size
//            val want_to_play = tui.next_player_Card(game.players(current_player))
//            if (Cards.isPlayable(serve_card, want_to_play, game.players(current_player).hand)) {
//              not_playable = false
//              mini_cards = mini_cards.appended(want_to_play)
//              game = game.playCard(want_to_play, current_player)
//              if ((mini_round_player == 0) || (serve_card.num == 0)) {
//                serve_card = want_to_play
//              }
//            } else {
//              tui.card_not_playable()
//            }
//          }
//        }
//        //mini_starter setzten
//        mini_starter = game.calc_mini(mini_cards, trumpcard, mini_starter)
//        made(mini_starter) += 1
//        tui.mini_ended(game.players(mini_starter))
//      }
//      //alle mini runden vorbei (runden vorbei)
//      game = game.round_finished(made)
//      tui.round_ended(game)
    }
    //Letzte Runde beendet (for schleife verlassen)
    println("Gesamtergebnis: " + game.calc_total())
    println("Wizard is over!")
  }

  def play_round(round_number: Int): Unit {

  }

  def generate_Cards(): Unit {

  }
}
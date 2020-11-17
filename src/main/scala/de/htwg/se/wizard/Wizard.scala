package de.htwg.se.wizard

import de.htwg.se.wizard.control.Controller
import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.model._

object Wizard {

    val controller = new Controller(Gamestate())
    val tui = new TUI(controller)
    controller.notify_Observer()



  def main(args: Array[String]): Unit = {
    println("Willkommen zu Wizzard\n\n")
    tui.createPlayers()

    for (round_number <- 0 until number_of_rounds()) {
      play_round(round_number)
    }
    println("Wizard is over!")
  }




  def play_round(round_number: Int): Unit = {
    tui.begin_round(round_number)

    generate_Cards()

    get_guesses()

    for (_ <- 0 until round_number) play_mini_round()

    tui.round_finished()
  }

  def generate_Cards(): Unit = {
    tui.generate_hands()
  }

  def number_of_rounds(): Int = {
    60 / controller.game.players.size
  }

  def play_mini_round()= {
    //ründchenstarter (am anfang round_number-1, danach der winner)
    //var mini_starter = round_number - 1
    //var mini_cards = List[Card]()
    //            var serve_card = Cards.all_cards(0)
    //            for (mini_round_player <- game.players.indices) { //jeder spieler in einem miniründchen
    //              var not_playable = true
    //              while (not_playable) {
    //                val current_player = (mini_starter + mini_round_player) % game.players.size
    //                val want_to_play = tui.next_player_Card(game.players(current_player))
    //                if (Cards.isPlayable(serve_card, want_to_play, game.players(current_player).hand)) {
    //                  not_playable = false
    //                  mini_cards = mini_cards.appended(want_to_play)
    //                  game = game.playCard(want_to_play, current_player)
    //                  if ((mini_round_player == 0) || (serve_card.num == 0)) {
    //                    serve_card = want_to_play
    //                  }
    //                } else {
    //                  tui.card_not_playable()
    //                }
    //              }
//    mini_starter setzten
    //tricks made hoch zählen
//    mini_starter = game.calc_mini(mini_cards, trumpcard, mini_starter)
//    made(mini_starter) += 1
//    tui.mini_ended(game.players(mini_starter))
  }

  def get_guesses(): Unit = {
    tui.get_guesses()
  }

  def set_trump_Card(): Unit = {
   tui.generate_trump_card()
  }
}


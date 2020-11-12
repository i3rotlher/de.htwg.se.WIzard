package de.htwg.se.wizard

import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.model._

object Wizard {
  def main(args: Array[String]): Unit = {
    val tui = TUI()
    var game = Gamestate()


    printf("Willkommen zu Wizzard\n")
    game = tui.createPlayers()

    //Anfang For Schleife des Spiels
    for (round_number <- 1 to (60 / game.players.size)) {
      tui.begin_round(round_number)

      //Karten generieren
      game = game.generate_Hands(round_number)

      //erzeuge Trumpcard (übrige karte nach hands) und zeige Sie
      val trumpcard = game.get_Trump_card(game.players)
      tui.show_Trumpcard(trumpcard)
      // evtl. if mit letzter runde

      //anzahl stiche abfragen
      game = game.round_start(tui.get_guesses(game, round_number))

      //gewonnene stiche zählen
      val made = Array.fill(game.players.size) {0}

      //ründchenstarter (am anfang roundnumber-1, danach der winner)
      var mini_starter = round_number - 1
      var mini_cards = List[Card]()

      //ründchenanzahl = counter
      for (mini_round_number <- 0 until round_number) { //alle mini ründchen
        var serve_card = Cards.all_cards(0)
        for (mini_round_player <- game.players.indices) { //jeses spieler in einem miniründchen
          var not_playable = true
          while (not_playable) {
            val current_player = (mini_starter + mini_round_player) % game.players.size
            val want_to_play = tui.next_player_Card(game.players(current_player))
            if (Cards.isPlayable(serve_card, want_to_play, game.players(current_player).hand)) {
              not_playable = false
              mini_cards = mini_cards.appended(want_to_play)
              if ((mini_round_player == 0) || (serve_card.num == 0)) {
                serve_card = want_to_play
              }
            }
          }
        }
        //mini_starter setzten
        mini_starter = game.calc_mini(mini_cards, trumpcard, mini_starter)
        made(mini_starter) += 1
        tui.mini_ended(game.players(mini_starter))
      }
      //alle mini runden vorbei (runden vorbei)
      game = game.round_finished(made)
      tui.round_ended(game)
    }
    //Letzte Runde beendet (for schleife verlassen)
    println(game.game_table)
    println(game.calc_total())
    println("Wizard is over!")
  }
}

////    var first_ruendchen = true
////    var ruendchenwinner = 0
////    for (_counter <- 0 until (round_number)) {
////      var round_player = 0
////      if (first_ruendchen) {
////        round_player = ((round_number) % game.players.size) - 1
////      }
////      else {
////        round_player = ruendchenwinner
////      }
////
////      // for playeranzahl
////      val playerarray = game.players.toArray
////
////
////      // welche Karte willst du spielen? --> while(isPlayable(nextPlayercard(Spielerobjekt))== true// ende
////      //Hier ändern
////      var firstcard = Cards.all_cards(0)
////      var first_time = true;
////      var round_cards = List[Card]()
////      val active_player = round_number % playerarray.length
////      for (i <- 0 until (game.players.size)) {
////        var allowed_card = false
////        var want_to_play = Cards.all_cards(0)
////
////        do {
////          want_to_play = tui.next_player_Card(active_player)
////          val handCards = active_player.hand
////          allowed_card = Cards.isPlayable(firstcard, want_to_play, handCards)
////          if (first_time) {
////            firstcard = want_to_play
////            first_time = false
////          }
////        } while (allowed_card == false)
////        round_cards = round_cards.appended(want_to_play) // Karten sind so wie sie gespielt wurden
////      }
////      //ründchen auswerten
////      val tmp = game.calc_mini(round_cards, trumpcard, round_player)
////      ruendchenwinner = tmp
////      won(tmp) += 1
////      tui.mini_ended(playerarray(tmp))
////    }
////    // ende des letzten Rünchens -> anzahl gemachter stiche
////    //Rundenende (Auswertung)
////    game = game.round_finished(won)
////    tui.round_ended(game)
////
////  }
////  // Ende For-Schleife des Spiels
////  //letze Runde beendet = gametable.size == (60/players.size)
////  //Ergebnisse ausgeben
////  print(game.game_table)
////  print("Wizard is over!")
////}
//}
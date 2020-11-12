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
    for (counter <- 1 to (60 / game.players.size)) {
      //Karten generieren
      game = game.generate_Hands(counter)


      //anzahl stiche abfragen
      game = game.round_start(tui.get_guesses(game, counter))

      //for Ründenanzahl
      var won = Array[Int](game.players.size)
      var first_ruendchen = true
      var ruendchenwinner = 0
      for (_counter <- 0 until (counter)) {
        var active_Player = 0
        if (first_ruendchen) {
          active_Player = ((counter - 1) % game.players.size) + 1
        }
        else {
          active_Player = ruendchenwinner
        }
        // welche Karte willst du spielen? --> while(isPlayable(nextPlayercard(Spielerobjekt))== true// ende
        // for playeranzahl
        val playerarray = game.players.toArray

        //HIer ändern
        val firstcard = Cards.all_cards(0)
        //Trumpcolor

        val trumpard = game.get_Trump_card(game.players)
        var round_cards = List[Card]()
        for (i <- 0 until (game.players.size)) {
          var allowed_card = false
          var want_to_play = Cards.all_cards(0)
          val round_player = playerarray(active_Player + i % game.players.size)
          do {
            want_to_play = tui.next_player_Card(round_player)
            val handCards = round_player.hand
            allowed_card = Cards.isPlayable(firstcard, want_to_play, handCards)
          } while (allowed_card == false)
          round_cards = round_cards.appended(want_to_play) // Karten sind so wie sie gespielt wurden
        }
        //ründchen auswerten
        val tmp = game.calc_mini(round_cards, trumpard, active_Player)
        ruendchenwinner = tmp
        won(tmp) += 1
      }
    // ende des letzten Rünchens -> anzahl gemachter stiche
    //Rundenende (Auswertung)
    game = game.round_finished(won)

    }
        // Ende For-Schleife des Spiels
    //letze Runde beendet = gametable.size == (60/players.size)
    //Ergebnisse ausgeben
    print(game.game_table)
    print("Wizard is over!")
  }
}
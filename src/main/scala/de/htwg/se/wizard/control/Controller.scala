package de.htwg.se.wizard.control

import de.htwg.se.wizard.model.{Card, Cards, Gamestate, Player, Round}
import de.htwg.se.wizard.util.Observable

class Controller(var game: Gamestate) extends Observable {

  def player_amount(): Int = game.players.size

  def set_trump_card(): Gamestate = {
    game = game.set_Trump_card(game.players, game.round_number)
    notify_Observer("trumpcard_set")
    game
  }

  //evtl gibt Gamestate zurück zu Testzwecken
  def create_Players(player_names: List[String]): Gamestate = {
    var players = List[Player]()
    for (x <- player_names)
      players = players :+ Player(x)
    game = game.create_players(players)
    notify_Observer("players_created")
    game
  }


  def generate_hands(round_number: Int, players: List[Player]): Gamestate = {
    game = game.generate_Hands(round_number, players)
    notify_Observer("generated_hands")
    game
  }

  def set_guess(guess: Int): Gamestate = {
    game = game.set_guess(guess)
    if (game.active_Player_idx == game.round_number%player_amount()) {
      notify_Observer("guesses_set")
    } else {
      notify_Observer("next_guess")
    }
    game
  }


  def play_card(want_to_play: Card): Gamestate = {
    val active_player_idx = game.active_Player_idx
    val mini_starter_idx = game.mini_starter_idx
    if (!card_playable(game.players(game.active_Player_idx), want_to_play, game.serve_card)) {
      notify_Observer("card_not_playable") //--> not playable choose other card , show hand again
      game
    } else {
      if (active_player_idx == mini_starter_idx + player_amount() - 1) { //letzter spieler
        game = game.end_mini(game.playedCards, game.trump_Card, game.mini_starter_idx)
        if (game.mini_played_counter == game.round_number + 1) {
          game = game.round_finished(game.made_tricks)
          notify_Observer("round_over")
          game
        } else {
          notify_Observer("mini_over")
          game
        }
      } else {
        game = game.playCard(want_to_play, game.players(active_player_idx))
        notify_Observer("next_player_card")
        game
      }
    }
  }

  def card_playable(active_player: Player, want_to_play: Card, serve_card: Card): Boolean = {
    if (Cards.isPlayable(serve_card, want_to_play, active_player.hand)) {
      game = game.playCard(want_to_play, active_player)
      true
    } else {
      false
    }
  }

  def play_round(round_number: Int): Gamestate = {
    game = generate_hands(round_number, game.players)
    game = set_trump_card()
    notify_Observer("round_started")
    game
  }

  def get_player(idx: Int) : Player = game.players(idx)

  def active_player_idx(): Int = game.active_Player_idx

  def get_mini_winner(): Player = game.players(game.mini_starter_idx)
}

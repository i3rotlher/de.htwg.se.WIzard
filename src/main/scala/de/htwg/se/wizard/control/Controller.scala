package de.htwg.se.wizard.control

import de.htwg.se.wizard.model.{Card, Cards, Gamestate, Player, Round}
import de.htwg.se.wizard.util.Observable

class Controller(var game: Gamestate) extends Observable {

  def player_amount(): Int = game.players.size

  def set_trump_card(): Gamestate = {
    game = game.set_Trump_card(game.players, game.round_number)
    if (game.trump_Card.num == 14) {
      notify_Observer(State.Wizard_trump)
      notify_Observer(State.set_Wizard_trump)
      return game
    }
    notify_Observer(State.next_guess)
    game
  }

  def generate_hands(round_number: Int, players: List[Player]): Gamestate = {
    game = game.generate_Hands(round_number, players)
    game
  }

  def set_guess(guess: Int): Gamestate = {
    game = game.set_guess(guess)
    if (game.active_Player_idx == game.round_number%player_amount()) {
      notify_Observer(State.next_player_card)
      game
    } else {
      notify_Observer(State.next_guess)
      game
    }
  }

  def play_card(want_to_play: Card): Gamestate = {
    new Strategy_playCard(this, want_to_play)
    game
  }

  def card_playable(active_player: Player, want_to_play: Card, serve_card: Card): Boolean = {
    Cards.isPlayable(serve_card, want_to_play, active_player.hand)
  }

  def start_round(round_number: Int): Gamestate = {
    game = generate_hands(round_number, game.players)
    game = set_trump_card()
    game
  }

  def get_player(idx: Int) : Player = game.players(idx)

  def active_player_idx(): Int = game.active_Player_idx

  def get_mini_winner(): Player = game.players(game.mini_starter_idx)

  def wish_trump(color: String) : Gamestate = {
    game = game.wish_trumpcard(color)
    notify_Observer(State.next_guess)
    game
  }

  def set_player_amount(amount: Int): Gamestate = {
    game = game.set_player_amount(amount)
    notify_Observer(State.player_create)
    game
  }

  def create_player(player_name: String): Gamestate = {
    game = game.create_player(player_name)
    if (game.active_Player_idx == 0) {
      notify_Observer(State.start_round)
      start_round(game.round_number)
      return game
    }
    notify_Observer(State.player_create)
    game
  }
}
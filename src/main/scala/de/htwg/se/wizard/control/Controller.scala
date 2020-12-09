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
    val active_player_idx = game.active_Player_idx
    val mini_starter_idx = game.mini_starter_idx
    if (!card_playable(game.players(game.active_Player_idx), want_to_play, game.serve_card)) {
      notify_Observer(State.card_not_playable)
      game
    } else {
      if (active_player_idx == (mini_starter_idx+player_amount()-1)%player_amount()) {
        game = game.playCard(want_to_play, game.players(active_player_idx))
        game = game.end_mini(game.playedCards, game.trump_Card, game.mini_starter_idx)
        notify_Observer(State.mini_over)
        if (game.mini_played_counter == game.round_number + 1) {
          if (game.round_number + 1 == 60/player_amount()) {
            game = game.round_finished(game.made_tricks)
            notify_Observer(State.round_over)
            notify_Observer(State.game_over)
            game
          } else {
            game = game.round_finished(game.made_tricks)
            notify_Observer(State.round_over)
            notify_Observer(State.start_round)
            start_round(game.round_number)
            game
          }
        } else {
          notify_Observer(State.next_player_card)
          game
        }
      } else {
        game = game.playCard(want_to_play, game.players(active_player_idx))
        notify_Observer(State.next_player_card)
        game
      }
    }
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

  def set_player_amount(amount: Option[Int]): Gamestate = {
    amount match {
      case Some(value) =>
        game = game.set_player_amount(value)
        notify_Observer(State.player_create)
        game
      case None =>
        game
    }
  }

  def create_player(player_name: String): Gamestate = {
    val Undo_Player_Name = new Undo_Player_Name(player_name, this)
    undoManager.doStep(Undo_Player_Name)
    notify_Observer(State.name_ok)

    if (game.active_Player_idx == 0) {
      notify_Observer(State.start_round)
      start_round(game.round_number)
      return game
    }
    notify_Observer(State.player_create)
    game
  }

  def undo_player: Unit = {
    undoManager.undoStep
    notify_Observer(State.player_create)
  }

  def redo_player: Unit = {
    undoManager.redoStep
  }

  val undoManager = new UndoManager
}
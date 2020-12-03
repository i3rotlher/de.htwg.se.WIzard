package de.htwg.se.wizard.control

import de.htwg.se.wizard.model.{Card, Gamestate}

class Strategy_playCard(controller: Controller, want_to_play: Card) {

  var strategy = {
    val active_player_idx = controller.game.active_Player_idx
    val mini_starter_idx = controller.game.mini_starter_idx
    if (!controller.card_playable(controller.game.players(controller.game.active_Player_idx), want_to_play, controller.game.serve_card)) {
      card_not_playable
    } else {
      if (active_player_idx == (mini_starter_idx+controller.player_amount()-1)%controller.player_amount()) {
        controller.game = controller.game.playCard(want_to_play, controller.game.players(controller.active_player_idx))
        controller.game = controller.game.end_mini(controller.game.playedCards, controller.game.trump_Card, controller.game.mini_starter_idx)
        controller.notify_Observer(State.mini_over)
        if (controller.game.mini_played_counter == controller.game.round_number + 1) {
          if (controller.game.round_number + 1 == 60/controller.player_amount()) {
            game_over
          } else {
            round_over
          }
        } else {
          mini_over
        }
      } else {
        next_player_card
      }
    }
  }

  def card_not_playable: Gamestate = {
    controller.notify_Observer(State.card_not_playable)
    controller.game
  }

  def game_over: Gamestate = {
    controller.game = controller.game.round_finished(controller.game.made_tricks)
    controller.notify_Observer(State.round_over)
    controller.notify_Observer(State.game_over)
    controller.game
  }

  def mini_over = {
    controller.notify_Observer(State.next_player_card)
    controller.game
  }

  def round_over = {
    controller.game = controller.game.round_finished(controller.game.made_tricks)
    controller.notify_Observer(State.round_over)
    controller.notify_Observer(State.start_round)
    controller.start_round(controller.game.round_number)
    controller.game
  }

  def next_player_card = {
    controller.game = controller.game.playCard(want_to_play, controller.game.players(controller.active_player_idx))
    controller.notify_Observer(State.next_player_card)
  }

}

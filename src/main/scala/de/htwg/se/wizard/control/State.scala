package de.htwg.se.wizard.control

object State extends Enumeration {
  val set_Wizard_trump, game_started, get_Amount, player_create, round_started, start_round, Wizard_trump, next_guess, next_player_card, round_over, card_not_playable, guesses_set, mini_over, game_over = Value
}

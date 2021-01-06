package de.htwg.se.wizard.control.controllerComponent

import de.htwg.se.wizard.model.cardsComponent.Card
import de.htwg.se.wizard.model.playerComponent.Player
import de.htwg.se.wizard.model.gamestateComponent.Gamestate
import de.htwg.se.wizard.util.UndoManager

import scala.swing.Publisher

trait ControllerInteface extends Publisher{
    def player_amount(): Int
    def set_trump_card(): Gamestate
    def generate_hands(round_number: Int, players: List[Player]): Gamestate
    def set_guess(guess: Int): Gamestate
    def play_card(want_to_play: Card): Gamestate
    def card_playable(active_player: Player, want_to_play: Card, serve_card: Card): Boolean
    def start_round(round_number: Int): Gamestate
    def get_player(idx: Int) : Player
    def active_player_idx(): Int
    def get_mini_winner(): Player
    def wish_trump(color: String) : Gamestate
    def set_player_amount(amount: Option[Int]): Gamestate
    def create_player(player_name: String): Gamestate
    def undo_player(): Unit
    def redo_player(): Unit
    val undoManager = new UndoManager()
}

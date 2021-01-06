package de.htwg.se.wizard.control

import de.htwg.se.wizard.model.cardsComponent.Card
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.Gamestate
import de.htwg.se.wizard.model.gamestateComponent.GamestateInterface
import de.htwg.se.wizard.model.playerComponent.PlayerBaseImpl.Player
import de.htwg.se.wizard.model.playerComponent.PlayerInterface
import de.htwg.se.wizard.util.UndoManager

import scala.swing.Publisher

trait ControllerInteface extends Publisher{
    def player_amount(): Int
    def set_trump_card(): GamestateInterface
    def generate_hands(round_number: Int, players: List[PlayerInterface]): GamestateInterface
    def set_guess(guess: Int): GamestateInterface
    def play_card(want_to_play: Card): GamestateInterface
    def card_playable(active_player: PlayerInterface, want_to_play: Card, serve_card: Card): Boolean
    def start_round(round_number: Int): GamestateInterface
    def get_player(idx: Int) : PlayerInterface
    def active_player_idx(): Int
    def get_mini_winner(): PlayerInterface
    def wish_trump(color: String) : GamestateInterface
    def set_player_amount(amount: Option[Int]): GamestateInterface
    def create_player(player_name: String): GamestateInterface
    def undo_player(): Unit
    def redo_player(): Unit
    def getGamestate(): GamestateInterface
    val undoManager = new UndoManager()
}

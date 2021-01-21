package de.htwg.se.wizard.model.gamestateComponent
import de.htwg.se.wizard.model.cardsComponent.Card
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.Round
import de.htwg.se.wizard.model.playerComponent.PlayerBaseImpl.Player
import de.htwg.se.wizard.util.PlayerStrategy

trait GamestateInterface extends PlayerStrategy {

    def getMini_played_counter: Int
    def getMini_starter: Int
    def getPlayedCards: List[Card]
    def getMade_tricks: List[Int]
    def getServe_card: Card
    def getTrump_card: Card
    def getPlayers: List[Player]
    def getGame_table: List[Round]
    def getRound_number: Int
    def getActive_player_idx: Int
    def round_finished(made: Iterable[Int]): GamestateInterface
    def set_guess(guessed_tricks: Int): GamestateInterface
    def create_player(player_name: String): GamestateInterface
    def end_mini(played_cards_in_played_order: Iterable[Card], trump: Card, first_player_index: Int): GamestateInterface
    def generate_Hands(round_number: Int, players: List[Player]): GamestateInterface
    def calc_total(): List[Int]
    def set_Trump_card(player: List[Player], round_nr: Int): GamestateInterface
    def playCard(played_card: Card, active_player: Player): GamestateInterface
    def wish_trumpcard(color: String): GamestateInterface
    def set_player_amount(amount: Int): GamestateInterface
    def set_active_player_idx(idx: Int): GamestateInterface
    def reset_player(): GamestateInterface
    override def strategy(amount_of_players: Int): GamestateInterface
    override def strategy_3_players(): GamestateInterface
    override def strategy_4_players(): GamestateInterface
    override def strategy_5_players(): GamestateInterface
    override def strategy_6_players(): GamestateInterface
}

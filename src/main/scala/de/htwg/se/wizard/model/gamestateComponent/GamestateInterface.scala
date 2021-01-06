package de.htwg.se.wizard.model.gamestateComponent
import de.htwg.se.wizard.model.cardsComponent.Card
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.{Gamestate, PlayerStrategy}
import de.htwg.se.wizard.model.playerComponent.Player

trait GamestateInterface extends PlayerStrategy {

    def round_finished(made: Iterable[Int]): Gamestate

    def set_guess(guessed_tricks: Int): Gamestate

    def create_player(player_name: String): Gamestate

    def end_mini(played_cards_in_played_order: Iterable[Card], trump: Card, first_player_index: Int): Gamestate

    def generate_Hands(round_number: Int, players: List[Player]): Gamestate

    def calc_total(): List[Int]

    def set_Trump_card(player: List[Player], round_nr: Int): Gamestate

    def playCard(played_card: Card, active_player: Player): Gamestate

    def wish_trumpcard(color: String): Gamestate

    def set_player_amount(amount: Int): Gamestate


    override def strategy(amount_of_players: Int): Gamestate

    override def strategy_3_players(): Gamestate

    override def strategy_4_players(): Gamestate

    override def strategy_5_players(): Gamestate

    override def strategy_6_players(): Gamestate
}

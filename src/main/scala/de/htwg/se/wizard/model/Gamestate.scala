package de.htwg.se.wizard.model

case class Gamestate(players:List[Player]=List(), game_table:List[Round]=List()) {

  def round_finished(made: Iterable[Int]) : Gamestate = {
      val finished_round = game_table.last.madeTricks(made)
      val new_game_table = game_table.updated(game_table.size-1, finished_round)
      Gamestate(players, new_game_table)
  }

  def round_start(guessed_tricks : List[Int]) : Gamestate = Gamestate(players, game_table.appended(Round(guessed_tricks)))

  def init_Gamestate(players : List[Player]): Gamestate = Gamestate(players)

  def calc_mini(played_cards_in_played_order : Iterable[Card], trump : Card, first_player_index: Int): Int = {
    (Cards.calcWinner(played_cards_in_played_order, trump.colour) + first_player_index) % players.size
  }

  def generate_Hands(round_nr :Int): Gamestate = {
    var tmpCard_Tuple = (List[Card](),List[Card]())
    var n_playerList = players
    for (i <- 0 until players.size) {
      tmpCard_Tuple = Cards.generateHand(round_nr, tmpCard_Tuple._2)
      n_playerList = n_playerList :+ Player(players(i).name, tmpCard_Tuple._1)
    }
    Gamestate(n_playerList, game_table)
  }

  def calc_total(): List[Int] = {
    var total = List[Int]()
    for (x <-0 until players.size) game_table.foreach(round => total = total.updated(x, total(x) + round.results(x)))
    total
  }
  def get_Trump_card(player:List[Player]): Card = {
    var usedCards = List[Card]()
    for (i<-player) {
      usedCards = usedCards.appendedAll(i.hand)
    }
    Cards.generateHand(1,usedCards)._1.head
  }
}

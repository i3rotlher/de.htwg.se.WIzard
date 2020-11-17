package de.htwg.se.wizard.model

case class Gamestate(players:List[Player]=List(), game_table:List[Round]=List(), round_number: Int = -1, active_Player_idx: Int = 0, trump_Card: Card = Cards.all_cards(0), made_tricks : List[Int] = List()) {

  def round_finished(made: Iterable[Int]) : Gamestate = {
      val finished_round = game_table.last.madeTricks(made)
      val new_game_table = game_table.updated(game_table.size-1, finished_round)
      Gamestate(players, new_game_table)
  }

  def set_guesses(guessed_tricks : List[Int]) : Gamestate = Gamestate(players, game_table.appended(Round(guessed_tricks)), round_number = round_number+1)

  def create_players(players : List[Player]): Gamestate = Gamestate(players, made_tricks = List.fill(players.size){0})

  def calc_mini(played_cards_in_played_order : Iterable[Card], trump : Card, first_player_index: Int): Int = {
    (Cards.calcWinner(played_cards_in_played_order, trump.colour) + first_player_index) % players.size
  }

  def generate_Hands(round_number: Int, players: List[Player]): Gamestate = {
    var tmpCard_Tuple = (List[Card](),List[Card]())
    var n_playerList = players
    for (i <- players.indices) {
      tmpCard_Tuple = Cards.generateHand(round_number, tmpCard_Tuple._2)
      n_playerList = n_playerList.updated(i,Player(players(i).name, tmpCard_Tuple._1))
    }
    copy(players = n_playerList)
  }

  def calc_total(): List[Int] = {
    val total = Array.fill(players.size){0}
    for (x <-players.indices) game_table.foreach(round => total(x) += round.results(x))
    total.toList
  }
  def set_Trump_card(player:List[Player], round_nr: Int): Gamestate = {
    var usedCards = List[Card]()
    for (i<-player) {
      usedCards = usedCards.appendedAll(i.hand)
    }
    var trump = Cards.generateHand(1,usedCards)._1.head
    if (round_nr == 60/players.size) {
      trump = Cards.all_cards(0)
    }
    copy(trump_Card = trump)
  }

  def playCard(card :Card, player_idx : Int): Gamestate = {
    val updated_player = players(player_idx).playCard(card)
    copy(players = players.updated(player_idx, updated_player))
  }
}

package de.htwg.se.wizard.model

case class Gamestate(players: List[Player] = List(), game_table: List[Round] = List(), round_number: Int = 0,
                     active_Player_idx: Int = 0, trump_Card: Card = Cards.all_cards(0), serve_card: Card = Cards.all_cards(0),
                     made_tricks: List[Int] = List(), playedCards: List[Card] = List(), mini_starter_idx: Int = 0,
                     mini_played_counter : Int = 0) {

  def round_finished(made: Iterable[Int]): Gamestate = {
    val finished_round = game_table.last.madeTricks(made)
    val new_game_table = game_table.updated(game_table.size - 1, finished_round)
    copy(game_table = new_game_table, mini_starter_idx = (round_number + 1) % players.size, active_Player_idx = (round_number + 1) % players.size,
      round_number = round_number + 1, made_tricks = List.fill(players.size){0}, mini_played_counter = 0)
  }

  def set_guess(guessed_tricks: Int): Gamestate = {
    val updated_guesses = game_table.last.guessed_tricks.updated(active_Player_idx, guessed_tricks)
    val updated_game_table = game_table.updated(round_number, Round(updated_guesses))
    copy(game_table = updated_game_table, active_Player_idx = (active_Player_idx+1)%players.size)
  }

  def create_players(players: List[Player]): Gamestate = Gamestate(players, made_tricks = List.fill(players.size){0})

  def end_mini(played_cards_in_played_order: Iterable[Card], trump: Card, first_player_index: Int): Gamestate = {
    val winner_idx = (Cards.calcWinner(played_cards_in_played_order, trump.colour) + first_player_index) % players.size
    copy(mini_starter_idx = winner_idx, made_tricks = made_tricks.updated(winner_idx, made_tricks(winner_idx) + 1), playedCards = List(), mini_played_counter = mini_played_counter+1, active_Player_idx = winner_idx)
  }

  def generate_Hands(round_number: Int, players: List[Player]): Gamestate = {
    var tmpCard_Tuple = (List[Card](), List[Card]())
    var n_playerList = players
    for (i <- players.indices) {
      tmpCard_Tuple = Cards.generateHand(round_number+1, tmpCard_Tuple._2)
      n_playerList = n_playerList.updated(i, Player(players(i).name, tmpCard_Tuple._1))
    }
    copy(players = n_playerList, active_Player_idx = round_number % players.size, game_table = game_table.appended(Round(List.fill(players.size){0})))
  }

  def calc_total(): List[Int] = {
    val total = Array.fill(players.size) {0}
    for (x <- players.indices) {
      game_table.foreach(round => total.update(x, total(x)+round.results(x)))
    }
    total.toList
  }

  def set_Trump_card(player: List[Player], round_nr: Int): Gamestate = {
    var usedCards = List[Card]()
    for (i <- player) {
      usedCards = usedCards.appendedAll(i.hand)
    }
    var trump = Cards.generateHand(1, usedCards)._1.head
    if (round_nr == 60 / players.size) {
      trump = Cards.all_cards(0)
    }
    copy(trump_Card = trump)
  }

  def playCard(played_card: Card, active_player: Player): Gamestate = {
    val updated_player = players(players.indexOf(active_player)).playCard(played_card)
    if (active_player == players(mini_starter_idx) || serve_card.num == 0 && played_card.num != 0) {
      copy(players = players.updated(players.indexOf(active_player), updated_player), playedCards = playedCards.appended(played_card), serve_card = played_card, active_Player_idx = (active_Player_idx+1)%players.size)
    } else
      copy(players = players.updated(players.indexOf(active_player), updated_player), playedCards = playedCards.appended(played_card), active_Player_idx = (active_Player_idx+1)%players.size)
  }

}

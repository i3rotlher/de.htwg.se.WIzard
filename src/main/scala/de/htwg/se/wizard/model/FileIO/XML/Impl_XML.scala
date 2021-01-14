package de.htwg.se.wizard.model.FileIO.XML

import de.htwg.se.wizard.model.FileIO.File_IO_Interface
import de.htwg.se.wizard.model.cardsComponent.Card
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.{Gamestate, Round}
import de.htwg.se.wizard.model.playerComponent._

import scala.xml._

class Impl_XML extends File_IO_Interface {
  override def load: Gamestate = ???

  override def save(state: Gamestate): Unit = {
    print(generate_XML(state))
  scala.xml.XML.save("wizard.xml", generate_XML(state))
  }

  def generate_XML(game: Gamestate): Elem = {
    <Wizard>
      {gamestate_toXML(game)}
    </Wizard>
  }

  def gamestate_toXML(game: Gamestate): Elem = {
    <Gamestate>
      {players_toXML(game.players)}{gametabel_toXML(game.game_table)}<Roundnumber>
      {value_toXML(game.round_number)}
    </Roundnumber>
      <Active_player_idx>
        {value_toXML(game.active_Player_idx)}
      </Active_player_idx>

      <Trump_card>
        {card_toXML(game.trump_Card)}
      </Trump_card>
      <Serve_card>
        {card_toXML(game.serve_card)}
      </Serve_card>
      <Made_tricks>
        {game.made_tricks.foreach(trick => trick_toXML(trick))}
      </Made_tricks>
      <Played_cards>
        {game.playedCards.foreach(card => card_toXML(card))}
      </Played_cards>
      <Mini_starter_idx>
        {game.mini_starter_idx.toString}
      </Mini_starter_idx>
      <Mini_played_counter>
        {game.mini_played_counter}
      </Mini_played_counter>
    </Gamestate>
  }

  def players_toXML(players: List[PlayerInterface]): Elem = {
    <Players>
      {players.foreach(player => player_toXML(player))}
    </Players>
  }

//  def player_toXML(player: PlayerInterface): Elem = {
//    <Player>
//      <name>
//        {player.getName}
//      </name>
//      <handcards>{cards_toXML(player.getHand)}</handcards>
//    </Player>
//
//  }
// Funktioniert sollte aber auch mit oben laufen
//  def player_toXML(player: PlayerInterface): String = {
//    val p = {"<Player>\n"+
//      "<name>"+
//      player.getName+
//      "</name>"+
//      //<handcards>{cards_toXML(player.getHand)}</handcards>
//      "</Player>"}
//    p
//  }

  def cards_toXML(cards: List[Card]): Elem = {
    <Cards>
      {cards.foreach(card => card_toXML(card))}
    </Cards>
  }

  def card_toXML(card: Card): Elem = {
    <Card>
      <value>
        {card.num}
      </value>
      <color>
        {card.colour}
      </color>
    </Card>
  }


  def gametabel_toXML(game_table: List[Round]): Elem = {
    <Gametabel>
      {game_table.foreach(round => round_toXML(round))}
    </Gametabel>
  }

  def round_toXML(round: Round): Elem = {
    <Round>
      <Guessed_tricks>
        {round.guessed_tricks.foreach(value => value_toXML(value))}
      </Guessed_tricks>
    </Round>
  }

  def value_toXML(number: Int):Elem = {
    <value>
      {number.toString}
    </value>
  }

  def trick_toXML(i: Int):Elem = {
    <trick>
      {i.toString}
    </trick>
  }

}

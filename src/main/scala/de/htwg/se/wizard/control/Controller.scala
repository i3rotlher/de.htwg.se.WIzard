package de.htwg.se.wizard.control

import de.htwg.se.wizard.model.{Card, Gamestate, Player, Round}
import de.htwg.se.wizard.util.Observable

class Controller(var game: Gamestate) extends Observable {

  def player_amount(): Int = game.players.size

  def set_trump_card(): Unit = {
    game = game.set_Trump_card(game.players, game.round_number)
    notify_Observer()
  }


  //evtl gibt Gamestate zurück zu Testzwecken
  def create_Players(player_names: List[String]): Unit = {
    var players = List[Player]()
    for (x <- player_names)
      players = players :+ Player(x)
    game = game.create_players(players)
    notify_Observer()
  }

  //evtl gibt Gamestate zurück zu Testzwecken
  def mini_Round(): Gamestate = {

    notify_Observer()
  }

  def get_Tricks(): Gamestate = {

    notify_Observer()
  }

  def gamestateToString(): String = {
    game.toString() //IMPLEMENTIEREN !!!
  }

  def generate_hands(): Unit = {
    game = game.generate_Hands(game.round_number, game.players)
    notify_Observer()
  }

  def set_guesses(guesses: List[Int]): Gamestate = {
    game.set_guesses(guesses)
  }

  def get_gametable(): List[Round]= {
    game.game_table
  }

}

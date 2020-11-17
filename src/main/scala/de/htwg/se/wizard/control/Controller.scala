package de.htwg.se.wizard.control

import de.htwg.se.wizard.model.{Gamestate, Player}
import de.htwg.se.wizard.util.Observable

class Controller(var game: Gamestate) extends Observable {
  def Game(game: Gamestate): Unit = {
    for (round_number <- 1.to(60 / game.players.size)) {
      big_Round(round_number)
    }
    notify_Observer()
  }

  //evtl gibt Gamestate zurück zu Testzwecken
  def create_Players(player_names: List[String]): Unit = {
    var players = List[Player]()
    for (x <- player_names)
      players = players :+ Player(x)
    game = game.init_Gamestate(players)
  }

  //evtl gibt Gamestate zurück zu Testzwecken
  def big_Round(round_number: Int): Gamestate = {

    //generate Hands
    //generate Trumpcard
    //Stiche Abfragen

    //play all Miniround
    for (_ <- 0.until(round_number))
      mini_Round()

    notify_Observer()
  }

  //evtl gibt Gamestate zurück zu Testzwecken
  def mini_Round(): Gamestate = {

    notify_Observer()
  }

  def get_Tricks(): Gamestate = {

    notify_Observer()
  }


}

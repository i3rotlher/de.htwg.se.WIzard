package de.htwg.se.wizard.control

import de.htwg.se.wizard.model.Gamestate
import de.htwg.se.wizard.util.Observable

class Controller extends Observable{
  def Game(game:Gamestate): Unit ={
    for(round_number <- 1.to(60 / game.players.size)){
      val gam = big_Round(game)
    }
    notifyAll()
  }

  def big_Round(gamestate: Gamestate, round_number:Int): Gamestate ={
    //generate Hands
    //generate Trumpcard
    //Stiche Abfragen

    //play all Miniround
    for(_<-0.until(round_number)){
      //play mini
    }
    notifyAll()
  }

}

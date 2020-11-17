package de.htwg.se.wizard.aview


import de.htwg.se.wizard.util.Observer
import de.htwg.se.wizard.control.Controller
import de.htwg.se.wizard.model.{Player,Card,Gamestate}

import scala.util.{Failure, Success, Try}

class TUI(controller: Controller) extends  Observer{

  controller.add(this)

  def next_player_Card(player: Player): Card = {
    println(player.name + " du bist dran!")
    println(player.showHand())
    println("Welche Karte soll gespielt werden ?")
    var fail = true
    var playcard = getNumber
    while (fail) {
      if (!(playcard >= 1 && playcard <= player.hand.size)) {
        println("Bitte gebe eine gültige Karte ein!")
        println("Welche Karte soll gespielt werden ?")
        println(player.showHand())
        playcard = getNumber
      } else {
        fail = false
      }
    }
    println("\n\n\n\n\n\n\n")
    player.hand(playcard-1)
  }

  def getNumber: Int = {
    var fail = true
    var input = toInt(scala.io.StdIn.readLine())
    while (fail) {
      input match {
        case Failure(_) => println("Bitte gebe eine gültige Zahl ein!")
          input = toInt(scala.io.StdIn.readLine())
        case Success(_) => fail = false
      }
    }
    input.get
  }

  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  def createPlayers(): Unit = {
    println("Wie viele Spieler wollen spielen ? [3,4,5 oder 6]")
    var playercount = scala.io.StdIn.readLine()
    while (!List("3", "4", "5", "6").contains(playercount)) {
      println("Es können nur 3,4,5 oder 6 Spieler spielen!")
      playercount = scala.io.StdIn.readLine()
    }

    var players = List[String]()
    for (x <- 1 to playercount.toInt) {
      println("Bitte gib deinen Namen ein Spieler " + x + ":")
      players = players.appended(scala.io.StdIn.readLine())
    }
    controller.create_Players(players)
  }


  def get_guesses(): Unit = {
    var guessed = List.fill(controller.player_amount()){0}
    for (i <- guessed.indices) {
      val active_Player = (controller.game.round_number + i - 1) % controller.player_amount()
      println("\n\nDeine Karten:")
      println(controller.game.players(active_Player).showHand()+"\n")
      println(controller.game.players(active_Player).toString + " wie viele Stiche wirst du machen?")
      guessed = guessed.updated(active_Player, getNumber)
      println("\n\n\n\n\n\n\n")
    }
    controller.set_guesses(guessed)
  }

  def show_Trumpcard(trumpcard : Card): Unit = {
    println("Trumpfkarte: " + trumpcard)
  }

  def generate_hands(): Unit = {
    println("Verteile Karten . . .")
    controller.generate_hands()
  }

  def generate_trump_card(): Unit = controller.set_trump_card()

  // wichtig: welche daten darf der View haben ? Wenn Spieler 1 dran ist darf er nicht die Karten von spieler 2 sehen
  override def update(): Unit = println(controller.gamestateToString())

  def mini_ended(winner : Player): Unit = println("Stich gewonnen von " + winner.toString + "!")

  def round_finished(): Unit = println("\n\n- - - - - - - - - Runde vorbei - - - - - - - - -\n\n" +controller.get_gametable())

  def begin_round(round_number: Int): Unit = {
    println("\n\n\n- - - - - - - - Runde "+round_number+" beginnt! - - - - - - - - -\n\n")
  }

  def card_not_playable(): Unit = println("Diese Karte ist momentan nicht spielbar!")
}

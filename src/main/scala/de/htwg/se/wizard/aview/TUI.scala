package de.htwg.se.wizard.aview
import de.htwg.se.wizard.util.Observer
import de.htwg.se.wizard.control.Controller
import de.htwg.se.wizard.model.{Card, Gamestate, Player}

import scala.util.{Failure, Success, Try}

class TUI(controller: Controller) extends Observer{

  controller.add(this)

  override def update(status: String): Unit = {
    status match {
      case "players_created" =>
      case "round_started" => println("- - - - - - Round " + controller.game.round_number + " started - - - - - - -")
        controller.set_guess(get_guess())
      case "next_guess" =>
        controller.set_guess(get_guess())
      case "next_player_card" =>
        val to_play = next_player_Card(controller.get_player(controller.active_player_idx()))
        controller.play_card(to_play)
      case "round_over" =>
        println("Trick won by " + controller.get_mini_winner().name + "!")
        println("Round " + controller.game.round_number + " has ended!\n" + controller.game.game_table + "\n\n\n")
      case "card_not_playable" =>
        println("This card is not playable right now!")
        val to_play = next_player_Card(controller.get_player(controller.active_player_idx()))
        controller.play_card(to_play)
      case "guesses_set" =>
        val to_play = next_player_Card(controller.get_player(controller.active_player_idx()))
        controller.play_card(to_play)
      case "mini_over" =>
        println("Trick won by " + controller.get_mini_winner().name + "!")
        val to_play = next_player_Card(controller.get_player(controller.active_player_idx()))
        controller.play_card(to_play)
      case "game_started" => createPlayers()
      case _ => println("case unimplemented: " + status )
    }

  }

  def createPlayers(): Unit = {
    println("How many players want to play ? [3,4,5 or 6]")
    var playercount = scala.io.StdIn.readLine()
    while (!List("3", "4", "5", "6").contains(playercount)) {
      println("There may only be 3,4,5 or 6 players!")
      playercount = scala.io.StdIn.readLine()
    }
    var players = List[String]()
    for (x <- 1 to playercount.toInt) {
      println("Insert your name player " + x + ":")
      players = players.appended(scala.io.StdIn.readLine())
    }
    controller.create_Players(players)
  }

  def next_player_Card(player: Player): Card = {
    println("Serve_Card: "+ controller.game.serve_card)
    println("Trump_Card: "+ controller.game.trump_Card)
    println(player.showHand())
    println("Which card do you want to play ?")
    var fail = true
    var playcard = getNumber
    while (fail) {
      if (!(playcard >= 1 && playcard <= player.hand.size)) {
        println("Please insert a correct card number!")
        println("Which card do you want to play ?")
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
        case Failure(_) => println("Insert a correct number!")
          input = toInt(scala.io.StdIn.readLine())
        case Success(_) =>
          if (input.get < 0) {
            println("Insert a correct number (>= 0)!")
            input = toInt(scala.io.StdIn.readLine())
          } else {
            fail = false
          }
      }
    }
    input.get
  }

  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  def get_guess(): Int = {

    println(show_trump_card())
    println("\n\nYour cards:")
    println(controller.game.players(controller.game.active_Player_idx).showHand()+"\n")
    println(controller.game.players(controller.game.active_Player_idx).name + " how many tricks are you going to make?")
    val guessed = getNumber
    println("\n\n\n\n\n\n\n")
    guessed
  }

  def show_trump_card(): String = "Trump-Card: " + controller.game.trump_Card + "\n"
}

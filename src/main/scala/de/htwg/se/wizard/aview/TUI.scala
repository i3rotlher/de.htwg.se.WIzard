package de.htwg.se.wizard.aview
import de.htwg.se.wizard.util.Observer
import de.htwg.se.wizard.control.{Controller, State}

import scala.util.{Failure, Success, Try}

class TUI(controller: Controller) extends Observer{

  controller.add(this)
  var state: State.Value = State.game_started

  override def update(status: State.Value): Boolean = {
    status match {
      case State.game_started => game_start();true
      case State.get_Amount => state = status;true
      case State.start_round => start_round();true
      case State.Wizard_trump => wizard_trump();true
      case State.set_Wizard_trump => state = status;true
      case State.player_create =>
        println("Player " + controller.active_player_idx() + " whats your name ?")
        state = status;true
      case State.next_guess =>
        guess()
        state = status;true
      case State.next_player_card =>
        play_card()
        state = status;true
      case State.card_not_playable =>
        println("This card is not playable right now!\n Choose a different number!");true
      case State.mini_over =>
        println("Trick won by " + controller.get_mini_winner().name + "!");true
      case State.round_over =>
        println(controller.game.game_table);true
      case State.game_over =>
        println("Game Over!")
        println(controller.game.calc_total())
        state = status;true
      case _ => println("update state unimplemented: " + state );true
    }
  }

  def processInput(input: String) : Unit = {
    state match {
      case State.get_Amount => check_Amount(input)
      case State.player_create => create_player(input)
      case State.set_Wizard_trump => check_trump_wish(input)
      case State.next_guess => get_guess(input)
      case State.next_player_card => get_card(input)
      case _ => println("state unimplemented: " + state)
    }
  }

  def game_start(): Unit = {
    println("Welcome to Wizard!")
    println("How many players want to play ? [3,4,5 or 6]")
    controller.notify_Observer(State.get_Amount)
  }

  def check_Amount(input: String): Int = {
    if (!List("3", "4", "5", "6").contains(input)) {
      println("There may only be 3,4,5 or 6 players!")
      return -1
    }
    controller.set_player_amount(input.toInt)
    input.toInt
  }

  def create_player(input: String): Unit = {
    controller.create_player(input)
  }

  def start_round() : Unit = {
    println("- - - - - Round " + controller.game.round_number+1 + " started - - - - -")
    println("Generating hands . . .")
    println("Generating trumpcard . . .\n\n\n")
  }

  def wizard_trump(): Unit = {
    println("A wizard has been drawn as the trump card!")
    val player = controller.get_player((controller.active_player_idx()-1+controller.player_amount())%controller.player_amount())
    println(player.name + " which color do you want to be trump? [red,blue,yellow,green]")
    println("Your cards: " + player.showHand())
  }

  def check_trump_wish(input: String): Boolean = {
    if(!List("red","green","blue","yellow").contains(input)) {
      println("You may only choose one of these colors red,blue,yellow,green")
      return false
    }
    controller.wish_trump(input)
    true
  }

  def guess(): Unit = {
    val active_player = controller.get_player(controller.active_player_idx())
    println("Trump card: " + controller.game.trump_Card)
    println(active_player.name + " how many tricks are you going to make?")
    println(active_player.showHand()+"\n\n\n\n\n")
  }

  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  def get_guess(input: String):Boolean = {
    val integer = toInt(input)
    integer match {
      case Failure(_) => println("Insert a correct number!");false
      case Success(_) =>
        if (integer.get < 0) {
          println("Insert a correct number (>= 0)!")
          false
        } else {
        controller.set_guess(integer.get)
          true
        }
    }
  }

  def play_card(): Unit = {
    val active_player = controller.get_player(controller.active_player_idx())
    println("Trump card: " + controller.game.trump_Card)
    println(active_player.name + " which card do you want to play ?")
    println("Your cards: " + active_player.showHand()+"\n\n\n\n\n")
  }

  def get_card(input: String): Unit = {
    val active_player = controller.get_player(controller.active_player_idx())
    var list = List[String]()
    for (x <- 1 to active_player.hand.size) {
      list = list :+ x.toString
    }
    if (!list.contains(input)) {
      println("Insert a valid card number!")
    } else {
      controller.play_card(active_player.hand(input.toInt-1))
    }
  }
}
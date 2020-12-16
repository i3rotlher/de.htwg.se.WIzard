package de.htwg.se.sudoku.aview.gui

import java.awt.Dimension
import java.util.concurrent.Flow.Publisher

import de.htwg.se.wizard.control._
import javax.swing.SwingConstants
import javax.swing.plaf.basic.BasicArrowButton

import scala.swing._
import scala.swing.event._
import scala.util.{Failure, Success, Try}

class SwingGUI(controller: Controller) extends Frame {

  listenTo(controller)

  title = "Wizard"

  var state: Event = new game_started

  reactions+= {
    case event: game_started => game_start(); event
    case event: get_Amount => state = event
    case event: start_round => start_round(); event
    case event: Wizard_trump => wizard_trump(); event
    case event: set_Wizard_trump => state = event
    case event: player_create =>
      set_name(controller.active_player_idx()+1)
      //println("Player " + (controller.active_player_idx()+1) + " whats your name ? / press r to undo previous name / press y to redo change")
      state = event
    case event: name_ok => state = event
    case event: next_guess =>
      guess()
      state = event
    case event: next_player_card =>
      play_card()
      state = event
    case event: card_not_playable =>
      println("This card is not playable right now!\n Choose a different number!"); event
    case event: mini_over =>
      println("Trick won by " + controller.get_mini_winner().name + "!"); event
    case event: round_over =>
      println(controller.game.game_table); event
    case event: game_over =>
      println("Game Over!")
      println(controller.game.calc_total())
      state = event
    case ButtonClicked(b) => println(b.name);processInput(b.name)
    case EditDone(s) => if (s.text != "Enter your name here ...") {
      println(s.text)
      processInput(s.text)
    }
    //case _ => println("gui event unimplemented: " + _)
  }

  def processInput(input: String) : Unit = {
    state match {
      case event: get_Amount => check_Amount(input)
      case event: player_create => create_player(input)
      case event: set_Wizard_trump => check_trump_wish(input)
      case event: next_guess => get_guess(input)
      case event: next_player_card => get_card(input)
      case _ => println("state unimplemented: " + state)
    }
  }

  val amount_panel = new FlowPanel {
    contents += new Label("How many players ?")
    val button3 = new Button("3 Players") {
      name = "3"
    }
    val button4 = new Button("4 Players") {
      name = "4"
    }
    val button5 = new Button("5 Players"){
      name = "5"
    }
    val button6 = new Button("6 Players"){
      name = "6"
    }
    contents+=button3
    contents+=button4
    contents+=button5
    contents+=button6
  }
  listenTo(amount_panel.button3)
  listenTo(amount_panel.button4)
  listenTo(amount_panel.button5)
  listenTo(amount_panel.button6)

  def game_start(): Unit = {
    contents = amount_panel
    controller.publish(new get_Amount)
  }

  val set_name_panel = new FlowPanel {
    var label = new Label()
    contents += label
    var text_field = new TextField("Enter your name here ...", 14)
    val undo_previous = new Button("\u2190") {
      name = "r"
    }
    val redo_change = new Button("\u2192") {
      name = "y"
    }

    contents += undo_previous
    contents += text_field
    contents += redo_change
  }
  listenTo(set_name_panel.text_field)
  listenTo(set_name_panel.undo_previous)
  listenTo(set_name_panel.redo_change)


  val wish_trump = new FlowPanel {

  }

  val set_guess = new FlowPanel {

  }

  ////////////////////////////////////////
  val warning_next_player = new FlowPanel {

  }
  ///////////////////////////////////////

  //main Panel + Status leiste
  val playing_panel = new FlowPanel {

  }

  val round_over = new FlowPanel {

  }

  /////////////////////////////////////////
  val game_over = new FlowPanel {

  }
  ////////////////////////////////////////

  def set_name(activeplayer_idx: Int): Unit = {
    set_name_panel.text_field.text = "Enter your name here ..."
    set_name_panel.label.text = "Player " + activeplayer_idx + " whats your Name ?"
    contents = set_name_panel
  }

  def check_Amount(input: String): Int = {
    if (!List("3", "4", "5", "6").contains(input)) {
      println("There may only be 3,4,5 or 6 players!")
      return -1
    }
    controller.set_player_amount(Some(input.toInt))
    input.toInt
  }

  def create_player(input: String): Unit = {
    input match {
      case "y" => controller.redo_player()
      case "r" => controller.undo_player()
      case _ => controller.create_player(input)
    }
  }

  def start_round() : Unit = {
    println("- - - - - Round " + (controller.game.round_number+1) + " started - - - - -")
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

  visible = true
}
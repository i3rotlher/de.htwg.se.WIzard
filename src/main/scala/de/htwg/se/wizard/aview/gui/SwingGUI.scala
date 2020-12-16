package de.htwg.se.wizard.aview.gui
import de.htwg.se.wizard.control._
import de.htwg.se.wizard.model.Card
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event._
import scala.util.{Failure, Success, Try}

class SwingGUI(controller: Controller) extends Frame {

  listenTo(controller)

  override def closeOperation(): Unit = {
    System.exit(-1)
  }
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
      state = event
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
      //Zähler einbauen -> doppelte ausführung vermeiden
    case EditDone(s) => if (s.text != "Enter your name here ..." && s.text != "Enter your guess here ...") {
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

  def game_start(): Unit = {
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

    contents = amount_panel
    controller.publish(new get_Amount)
  }


  def wizard_trump(): Unit = {
    val player = controller.get_player((controller.active_player_idx()-1+controller.player_amount())%controller.player_amount())
    println("Your cards: " + player.showHand())
    val hand = controller.game.players(controller.active_player_idx()).hand

    for (card <- hand) {
      val card_label = new Label {
        icon = new ImageIcon(get_card_face_path(card))
      }
//      wish_trump.player_hand_panel.contents+=card_label
    }
//    wish_trump.label.text = "A wizard has been drawn as the trump card!\n" + player.name + " which color do you want to be trump?"
//    contents = wish_trump
  }


  def guess(): Unit = {

    val set_guess_panel = new FlowPanel {
      val label = new Label()
      val trump = Card_panel(controller.game.trump_Card)
      trump.label.text = "Trump card:"
      val hand = new Hand_panel(controller.game.players(controller.active_player_idx()).hand)
      var text_field = new TextField("Enter your guess here ...", 14)
      contents+=trump
      contents+=hand
      contents+=text_field
    }

    listenTo(set_guess_panel.text_field)

    val active_player = controller.get_player(controller.active_player_idx())
    set_guess_panel.label.text = active_player.name + " how many tricks are you going to make?"

    contents=set_guess_panel
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


  // ------------------------------------------------------------------------------------------

  val set_name_panel = new FlowPanel {
    val label = new Label("Player " + "X" + " whats your Name ?")
    contents += label
    val text_field = new TextField("Enter your name here ...", 14)
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

  listenTo(set_name_panel.undo_previous)
  listenTo(set_name_panel.text_field)
  listenTo(set_name_panel.redo_change)

  def set_name(activeplayer_idx: Int): Unit = {
    set_name_panel.label.text ="Player " + activeplayer_idx + " whats your Name ?"
    set_name_panel.text_field.text = "Enter your name here ..."

    contents=set_name_panel
  }

  // ------------------------------------------------------------------------------------------

  def create_player(input: String): Unit = {
    input match {
      case "y" => controller.redo_player()
      case "r" => controller.undo_player()
      case _ => controller.create_player(input)
    }
  }



  def check_Amount(input: String): Int = {
    if (!List("3", "4", "5", "6").contains(input)) {
      println("There may only be 3,4,5 or 6 players!")
      return -1
    }
    controller.set_player_amount(Some(input.toInt))
    input.toInt
  }

  def start_round() : Unit = {
    println("- - - - - Round " + (controller.game.round_number+1) + " started - - - - -")
    println("Generating hands . . .")
    println("Generating trumpcard . . .\n\n\n")
  }

  def check_trump_wish(input: String): Boolean = {
    if(!List("red","green","blue","yellow").contains(input)) {
      println("You may only choose one of these colors red,blue,yellow,green")
      return false
    }
    controller.wish_trump(input)
    true
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

  def get_card_face_path(card: Card): String = {
    if (card.colour.contains("none")) {
      return "C:\\Users\\maxek\\Desktop\\AIN\\Semester 3\\Software Engeneering\\de.htwg.se.Wizard\\Card_faces\\" + (card.colour.substring(card.colour.indexOf("(")+1,card.colour.indexOf("(")+2) + card.num) + ".png"
    }
    "C:\\Users\\maxek\\Desktop\\AIN\\Semester 3\\Software Engeneering\\de.htwg.se.Wizard\\Card_faces\\" + (card.colour.substring(0,1) + card.num) + ".png"
  }

  visible = true
}
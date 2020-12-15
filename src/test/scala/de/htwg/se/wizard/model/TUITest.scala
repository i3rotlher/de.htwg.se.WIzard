package de.htwg.se.wizard.model
import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.control.{Controller, State}
import de.htwg.se.wizard.util.{Observable, Observer}
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success, Try}

class TUITest extends AnyWordSpec {
  "The TUI" should {

    val controller = new Controller(Gamestate())
    controller.set_player_amount(Some(3))
    val tui = new TUI(controller)

    "have the state after the update is called" in {
      for (state <- State.values) {
        tui.update(state) shouldBe true
      }
    }
    "return -1 on failure when checking the amount input" in {
      tui.check_Amount("7") shouldBe(-1)
    }
    "return the value if it's valid on check amount" in{
      tui.check_Amount("4") shouldBe 4
    }
    "be able to Create a player when given a name" in {
      tui.create_player("Karl")
      controller.game.players(0).name shouldBe "Karl"
    }
    "be able to reset the ceated players name" in {
      tui.create_player("Karl")
      tui.create_player("r")
      controller.game.players(1).name shouldBe "unknown"
    }
    "be able to redo a players name" in {
      tui.create_player("Karl")
      tui.create_player("r")
      tui.create_player("y")
      controller.game.players(0).name shouldBe "Karl"
    }
    "return false if a wrong color has been inputed" in {
      tui.check_trump_wish("pink") shouldBe false
    }
    "return true if a correct coulor has been inputed" in {
      tui.check_trump_wish("green") shouldBe true
    }
    "get an int value" in {
      tui.toInt("1") shouldBe Success(1)
    }
    "leave the state as is if the input for a guess has been not valid" in {
      tui.get_guess("abc") shouldBe false
    }
    "just print the error without changing anything if the number was less than 0" in {
      tui.get_guess("-1") shouldBe false
    }
    "set the guess if the input was correct" in {

      var game = Gamestate().set_player_amount(3)
      game = game.create_player("Max")
      game = game.create_player("Niclas")
      game = game.create_player("Prof")
      controller.game = game
      controller.start_round(1)
      tui.get_guess("1") shouldBe true
    }
    "be able toselect a card the player wants to play" in {
      var game = Gamestate().set_player_amount(3)
      game = game.create_player("Max")
      game = game.create_player("Niclas")
      game = game.create_player("Prof")
      controller.game = game
      controller.start_round(1)
      val old_card = controller.game.players(controller.active_player_idx()).hand(0)
      tui.get_card("1")
      controller.game.players(controller.active_player_idx()).hand.contains(old_card) shouldBe false
    }
    "should not invoke play_card if the input for the card selection was incorrect" in {
      var game = Gamestate().set_player_amount(3)
      game = game.create_player("Max")
      game = game.create_player("Niclas")
      game = game.create_player("Prof")
      controller.game = game
      controller.start_round(1)
      val old_cards = controller.game.players(controller.active_player_idx()).hand
      tui.get_card("-a")
      controller.game.players(controller.active_player_idx()).hand shouldBe old_cards

    }
    "should invoke diffrent matches for each diffrent state it can be" in {
      for (state <- List(State.get_Amount, State.player_create, State.set_Wizard_trump,
        State.next_guess, State.next_player_card, State.game_over)) {
        tui.state = state
        tui.processInput("bla")
      }
    }
  }
}

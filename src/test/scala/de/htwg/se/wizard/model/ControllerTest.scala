package de.htwg.se.wizard.model

import de.htwg.se.wizard.control._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class ControllerTest extends AnyWordSpec {
  "A controller when created" should {
    val player_list = List[Player](new Player("Karl"), new Player("Bob"), new Player("Otto"))
    val sample_game_tabel = List[Round](Round(List(0,1,1),List(20,30,-10)))//Player 2 wins first round
    val reset_sample_state = new Gamestate()
    val reset_sample_control = new Controller(reset_sample_state)
    val reset_sample_state_2 = new Gamestate(player_list,sample_game_tabel,1,1,mini_starter_idx = 1)
    val reset_sample_control_state_2 = new Controller(reset_sample_state_2)
    var sample_State = reset_sample_state
    var control = reset_sample_control

    val string_player_list = List("Karl", "Bob", "Otto")

    "create players" in {
      control = reset_sample_control
      control.create_Players(string_player_list)
      control.game.players shouldBe (player_list)
    }
    "and show the player amount" in {
      control = reset_sample_control
      control.player_amount() shouldBe 3
    }
    "after the round started and the cards are distributed the player makes guesses " in {
      val tmp_round_list = List[Round](Round(List(0,1,1),List(20,30,-10)),Round(List(0,0,0),List(0,0,0)))//Player 2 wins first round
      control = new Controller(new Gamestate(player_list,tmp_round_list,1,1,mini_starter_idx = 1))
      var tmp_game = control.set_guess(4)
      tmp_game.game_table.last.guessed_tricks(1) shouldBe(4)

      control = new Controller(new Gamestate(player_list,tmp_round_list,1,0,mini_starter_idx = 1))
      tmp_game = control.set_guess(4)
    }
    "depending on the round number it generate the playercards (1 in round one and 2 in round two etc.)" in {
      control = reset_sample_control
      control.generate_hands(1, control.game.players)
      control.game.players(0).hand.size shouldBe (2)
      control.game.players(1).hand.size shouldBe (2)
      control.game.players(2).hand.size shouldBe (2)
    }
    "also the trump card should be generated" in{
      control = reset_sample_control
      while (control.game.trump_Card==Cards.all_cards(0))
        control.set_trump_card()
      control.game.trump_Card != Cards.all_cards(0) shouldBe true
    }

    "their is also a check if a player can play their card" in {
      val tmp_player_list = List[Player](new Player("Karl"), new Player("Bob", List(Cards.all_cards(4),Cards.all_cards(5),Cards.all_cards(6))), new Player("Otto"))
      control = new Controller( new Gamestate(tmp_player_list,sample_game_tabel,1,1,mini_starter_idx = 1))
      control.card_playable(control.game.players(1),control.game.players(1).hand(0),Cards.all_cards(8)) shouldBe(true)

      val tmp_player_list1= List[Player](new Player("Karl"), new Player("Bob", List(Cards.all_cards(4),Cards.all_cards(5),Cards.all_cards(6))), new Player("Otto"))
      control = new Controller( new Gamestate(tmp_player_list1,sample_game_tabel,1,1,mini_starter_idx = 1))

      control.card_playable(control.game.players(1),control.game.players(1).hand(0),Cards.all_cards(9)) shouldBe(false)

    }
    "you can play a round" in{
      control = reset_sample_control_state_2
      //wait till we have a different trump card than default trump card
      while(control.game.trump_Card == Cards.all_cards(0))
        control.play_round(1)
      control.game.players(0).hand.size shouldBe 2
    }
    "their is also the ability to resolve a player_index" in{
      control = reset_sample_control_state_2
      control.get_player(0) shouldBe(control.game.players(0))
      control.get_player(1) shouldBe(control.game.players(1))
      control.get_player(2) shouldBe(control.game.players(2))
    }
    "you can also show the index of the active player" in{
      control = reset_sample_control_state_2
      control.active_player_idx() shouldBe(1)
    }
    "also you can get the mini round winner index" in {
      control = reset_sample_control_state_2
      control.get_mini_winner() shouldBe (control.game.players(1))
    }
//    "take away the players card when playcard is called" in {
//      var state = reset_sample_control.generate_hands(5,reset_sample_control.game.players)
//      val cont = new Controller(state)
//      val comp_state = cont.play_card(state.players(0).hand(0))
//      comp_state.players(1).hand.contains(state.players(1).hand(0)) shouldBe false
//    }
  }
}

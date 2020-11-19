package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class GamestateTest extends AnyWordSpec {
  "Gamestate" when {
    "initialized with these players" should {
      var game = Gamestate().create_players(List(Player("Max"),Player("Niclas"),Player("Prof")))
      "have the players names" in {
        game.players shouldBe List(Player("Max"),Player("Niclas"),Player("Prof"))
      }
      "and when handing out the cards in round 5 they should all be different" in {
        game = game.generate_Hands(4, game.players)
        (game.players.head.hand diff game.players(1).hand).size shouldBe 5
        (game.players(1).hand diff game.players(2).hand).size shouldBe 5
      }
      "and generate a trumpcard out of the remaining cards" in {
        game = game.generate_Hands(5, game.players)
        game.set_Trump_card( game.players, 5)
        game.players.head.hand.contains(game.trump_Card) shouldBe false
        game.players(1).hand.contains(game.trump_Card) shouldBe false
        game.players(2).hand.contains(game.trump_Card) shouldBe false
      }
      "when the ticks are guessed and the round started have the guesses" in {
        game = game.set_guess(1)
        game = game.set_guess(2)
        game = game.set_guess(3)
        game.game_table.last.guessed_tricks shouldBe List(1,2,3)
      }
      "when the round is complete should have the round with the results in" in {
        game = game.set_guess(1)
        game = game.set_guess(2)
        game = game.set_guess(3)
        game = game.round_finished(List(1,2,2))
        game.game_table.last.results shouldBe List(30,40,-10)
      }
      "when a mini round (Stich) has been played calc mini should set the idx of the winner to mini starter for next round" in {
        game.end_mini(List(Cards.all_cards(0),Cards.all_cards(1),Cards.all_cards(58)), Cards.all_cards(20), 2).mini_starter_idx shouldBe 1
      }
      "when the game is over (2 Rounds in this case) and calc total is called should give the total points" in {
        game = Gamestate().create_players(List(Player("Max"),Player("Niclas"),Player("Prof")))
        game = game.set_guess(1)
        game = game.set_guess(2)
        game = game.set_guess(3)
        game = game.round_finished(List(1,2,2))
        game = game.set_guess(2)
        game = game.set_guess(3)
        game = game.set_guess(1)
        game = game.round_finished(List(1,2,2))
        game.calc_total() shouldBe List(60,80,-20)
      }
      "When a player played a card should have the remaining cards" in {
        game = game.generate_Hands(5, game.players)
        val to_play = game.players.head.hand.head
        game = game.playCard(to_play, game.players(0))
        game.players.head.hand.contains(to_play) shouldBe false
      }
    }
  }
}
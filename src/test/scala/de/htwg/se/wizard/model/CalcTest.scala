package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class CalcTest extends AnyWordSpec {// Warum falsch?
  "A card before being played" should{
    val handcards = List(Cards.all_cards(4),Cards.all_cards(19))
    "Be checked if they are allowed to be Played: When a wizzard was played first eyerything is allowed"in {
      Cards.isPlayable(Cards.all_cards(58), handcards.head, handcards) shouldBe(true)
    }
    "When you don't have the colour everything is allowed to be played" in {
      Cards.isPlayable(Cards.all_cards(5), handcards.head, handcards) shouldBe(true)
    }
    "When you have the colour and don't play it you can't" in {
      Cards.isPlayable(Cards.all_cards(4), handcards.last, handcards) shouldBe(false)
    }
  }
  "After every player played a card the winner of this round is calculated" should{
    "When 2 wizzards are played first wins" in{
      Cards.calcWinner(List(Cards.all_cards(58),Cards.all_cards(59),Cards.all_cards(4),Cards.all_cards(8)),"red") shouldBe(0)
    }
    "When every player played a fool the first wins" in {
      Cards.calcWinner(List(Cards.all_cards(3),Cards.all_cards(2),Cards.all_cards(0),Cards.all_cards(1)),"blue") shouldBe(0)
    }
    "When a wizzard is played it wins" in{
      Cards.calcWinner(List(Cards.all_cards(3),Cards.all_cards(2),Cards.all_cards(58),Cards.all_cards(23)),"green") shouldBe(2)
    }

    "When their is a trumpcard and no wizzard it wins" in{
      Cards.calcWinner(List(Cards.all_cards(5),Cards.all_cards(6),Cards.all_cards(7),Cards.all_cards(4)),"green") shouldBe(3)
    }

    "When there is no special highest number of the operating colour wins" in {
      Cards.calcWinner(List(Cards.all_cards(5),Cards.all_cards(6),Cards.all_cards(7),Cards.all_cards(4)),"green") shouldBe(3)
    }
  }
}
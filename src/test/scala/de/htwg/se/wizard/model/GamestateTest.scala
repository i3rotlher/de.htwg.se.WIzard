package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class GamestateTest extends AnyWordSpec {

  "A Gamestate" when {
    "created" should {
      var gamestate = Gamestate
      "add a Player" in {
        player.name shouldBe("Karl")
      }
      "added two players" in {
        player.hand shouldBe(List(Cards.all_cards(0),Cards.all_cards(1)))
      }
      "and when played a card" in {
        player = player.playCard(Cards.all_cards(1))
        player.hand shouldBe(List(Cards.all_cards(0)))
      }
      "and toString should look like" in {
        player.toString shouldBe("Karl")
      }
      "when unapplied" in {
        Player.unapply(player).get shouldBe ("Karl", List(Cards.all_cards(0)))
      }
    }
  }
}
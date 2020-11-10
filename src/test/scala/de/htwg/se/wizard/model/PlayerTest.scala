package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class PlayerTest extends AnyWordSpec {
  "A player" when {
      "created" should {
        var player = Player("Karl", List(Cards.all_cards(0),Cards.all_cards(1)))
        "have a name" in {
          player.name should be("Karl")
        }
        "and have the cards" in {
          player.hand should be(List(Cards.all_cards(0),Cards.all_cards(1)))
        }
        "and when played a card" in {
          player = player.playCard(Cards.all_cards(1))
          player.hand should be(List(Cards.all_cards(0)))
        }
        "and toString should look like" in {
          player.toString should be("Karl")
        }
        "when unapplied" in {
          Player.unapply(player).get should be ("Karl", List(Cards.all_cards(0)))
        }
    }
  }
}

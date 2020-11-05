package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
//import org.scalatest._ wieso geht das nicht

//import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper sorgt dafuer dass should falsch interpretiert wird WARUM ?


class PlayerTest extends AnyWordSpec {
  "A player" when {
      "created" should {
        val player = Player("Karl")
        "have a name" in {
          player.name should be("Karl")
      }
        "and toString should look like" in {
          player.toString should be("Karl")
        }
        "when unapplied" in {
          Player.unapply(player).get should be ("Karl")
        }
    }
  }
}

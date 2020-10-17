package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec


class PlayerTest extends AnyWordSpec {
  "A player" when { "new" should{
    val player = Player("Karl")
    "have a name" in {
      player.name shouldBe "Karl"
    }
    "and has a String representation" in{
      player.toString shouldBe "Karl"
    }
  }

  }
}

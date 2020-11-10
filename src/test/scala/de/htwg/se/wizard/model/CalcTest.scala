package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class CalcTest extends AnyWordSpec {// Warum falsch?
  "A card before being played" should{
    val handcards = List(Cards.all_cards(4),Cards.all_cards(19))
    "Be checked if they are allowed to be Played" in {
      Cards.isPlayable(Cards.all_cards(58), handcards.head, handcards) shouldBe(true)
    }

  }
}
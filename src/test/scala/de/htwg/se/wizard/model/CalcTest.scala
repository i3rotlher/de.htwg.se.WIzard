package de.htwg.se.wizard.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class CalcTest extends AnyWordSpec {// Warum falsch?
  "A card before being played" should{
    "Be checked if they are allowed to be Played" in {
      val handcards = List(Cards.all_cards(4),Cards.all_cards(19))
      Cards.isPlayable(Cards.all_cards(53), "red", handcards.head, handcards)

    }

  }
}
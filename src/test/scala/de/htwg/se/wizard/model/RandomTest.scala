package de.htwg.se.wizard.model
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class RandomTest extends AnyWordSpec {
  "Handcards when created" should {
    val tmpHand1 = Cards.generateHand(30)
    "in amount equal the roundcounter " in {
      tmpHand1._1.size shouldBe(30)
    }
    "player 1 have diffrent cards than player 2" in {
      val tmpHand2 = Cards.generateHand(30,tmpHand1._1)
      tmpHand2._1.diff(tmpHand1._1).size shouldBe(30)
    }
  }
}

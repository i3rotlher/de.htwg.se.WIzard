package de.htwg.se.wizard.model
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class RandomTest extends AnyWordSpec {
  "Handcards when created" should {
    "in amount equal the roundcounter " in {
      val tmpHand:(Iterable[Card],Iterable[Card]) = Cards.generateHand(2,List[Card]())
      tmpHand._1.size shouldBe(2)
    }
  }
}

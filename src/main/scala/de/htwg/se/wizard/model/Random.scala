package de.htwg.se.wizard.model

import scala.util.Random

trait Random {
  def generateHand(round: Int, used: Iterable[Card]=List[Card]()): (Iterable[Card], Iterable[Card]) = {
    var handCards:List[Card] = List[Card]()
    var usedCards: Seq[Card] = used.toSeq
    var nextCard = Cards.all_cards(0)
    for (_ <-0.until(round)){
      nextCard = Cards.all_cards.diff(usedCards)(Random.between(0,60-usedCards.size))
      handCards.appended(nextCard) //TODO: Warum wird next Card nicht in die handCards und usedCards eingefügt?
      usedCards :+ nextCard
    }
    (handCards,usedCards)
  }
}

package de.htwg.se.wizard.model

import scala.util.Random

trait Random {
  def generateHand(round: Int, used : List[Card] = List[Card]()) : (List[Card], List[Card]) = {
    var handCards = List[Card]().empty
    var usedCards = used
    var nextCard = Cards.all_cards(0)
    for (_ <-0.until(round)){
      nextCard = (Cards.all_cards diff usedCards)(Random.between(0,60-usedCards.size))
      handCards = nextCard :: handCards
      usedCards = nextCard :: usedCards
    }
    (handCards,usedCards)
  }
}

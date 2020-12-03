package de.htwg.se.wizard.model

trait Random {
  def generateHand(round: Int, used: List[Card] = List[Card]()): (List[Card], List[Card]) ={
    var handCards = List[Card]().empty
    var usedCards = used
    for (_ <- 0 until round) {
      val nextCard = (Cards.all_cards diff usedCards) (scala.util.Random.between(0, 60 - usedCards.size))
      handCards = nextCard :: handCards
      usedCards = nextCard :: usedCards
    }
    (handCards, usedCards)
  }
}

//class Random_standard extends Random {
//  override def generateHand(round: Int, used: List[Card] = List[Card]()): (List[Card], List[Card]) = {
//    var handCards = List[Card]().empty
//    var usedCards = used
//    for (_ <- 0 until round) {
//      val nextCard = (Cards.all_cards diff usedCards) (scala.util.Random.between(0, 60 - usedCards.size))
//      handCards = nextCard :: handCards
//      usedCards = nextCard :: usedCards
//    }
//    (handCards, usedCards)
//  }
//}
//
//class Radom_Infinity_Duplication extends Random {
//  override def generateHand(round: Int, used: List[Card]): (List[Card], List[Card]) = {
//    var handCards = List[Card]()
//    var usedCards = used
//    for (_ <- 0 until round) {
//      val nextCard = Cards.all_cards(scala.util.Random.between(0, 60))
//      handCards = nextCard :: handCards
//      usedCards = nextCard :: usedCards
//    }
//    (handCards, usedCards)
//  }
//}
//
//class Random_unique_Hand extends Random {
//  override def generateHand(round: Int, used: List[Card]): (List[Card], List[Card]) = {
//    var handCards = List[Card]().empty
//    var usedCards = List[Card]().empty
//    for (_ <- 0 until round) {
//      val nextCard = (Cards.all_cards diff usedCards) (scala.util.Random.between(0, 60 - usedCards.size)
//      handCards = nextCard :: handCards
//      usedCards = nextCard :: usedCards
//    }
//    (handCards,usedCards.appendedAll(used))
//
//  }
//}
package de.htwg.se.wizard.model

trait Calc {
  def isPlayable(firstPlayed: Card, trumpColour: String, toPlay: Card, handCards: Iterable[Card]): Boolean = { //TODO: vlt. List[Card]?
    if (toPlay.num == 0 || toPlay.num == 13)
      return true

    if (firstPlayed.colour == toPlay.colour)
      return true
    if (toPlay.colour == trumpColour)
      return true
    for (comp_Card <- handCards.filterNot(_ == toPlay)) {
      if (comp_Card.colour == trumpColour)
        return false
      if (comp_Card.colour == firstPlayed.colour)
        return false
    }
    true
  }

  def calcWinner(playedCards: Iterable[Card], trumpColour: String): Int = { //TODO: vlt. List[Card]? Wichtig played Cards muss in der Reihenfolge ichtig playedCards muss in der Reihenfolge in der die Karten gespielt wurden sortiert sein
    var winner = 0
    var winning_Card = playedCards.head
    var counter = 0
    for (comp_Card <- playedCards.filterNot(_ == winning_Card)) {
      if (winning_Card.num == 13) {
      } else if (comp_Card.num == 13) {
        winning_Card = comp_Card
        return counter
      } else if (comp_Card.colour == winning_Card.colour) {
        if (comp_Card.num > winning_Card.num) {
          winner = counter
          winning_Card = comp_Card
        }
      } else if (comp_Card.colour == trumpColour) {
        winner = counter
        winning_Card = comp_Card
      }
      counter += 1
    }
    winner
  }
}

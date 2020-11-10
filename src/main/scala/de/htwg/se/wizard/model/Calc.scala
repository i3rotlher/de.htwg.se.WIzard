package de.htwg.se.wizard.model

trait Calc {
  def isPlayable(firstPlayed: Card, toPlay: Card, handCards: Iterable[Card]): Boolean = { //TODO: vlt. List[Card]?
    if (toPlay.num == 0 || toPlay.num == 14)
      return true
    if (firstPlayed.colour == toPlay.colour)
      return true
    for (comp_Card <- handCards.filterNot(_ == toPlay)) {
      if (comp_Card.colour == firstPlayed.colour)
        return false
    }
    true
  }
//TODO: Letzte Runde Trupf deaktivieren
  def calcWinner(playedCards: Iterable[Card], trumpColour: String): Int = { //TODO: vlt. List[Card]? Wichtig played Cards muss in der Reihenfolge ichtig playedCards muss in der Reihenfolge in der die Karten gespielt wurden sortiert sein
    var winner: Int = 0
    var winning_Card = playedCards.head
    if (winning_Card.num == 14) {
      return winner
    }
    var counter:Int = 0
    for (comp_Card <- playedCards.filterNot(_ == winning_Card)) {
      counter += 1
      if (comp_Card.num == 14) {
        winning_Card = comp_Card
        return counter
      } else if (comp_Card.colour == winning_Card.colour) {
        if (comp_Card.num > winning_Card.num) {
          winner = counter
          winning_Card = comp_Card
        }
      } else if ((comp_Card.colour == trumpColour) && (winning_Card.num != 0)){
        winner = counter
        winning_Card = comp_Card
      }
    }
    winner
  }
}

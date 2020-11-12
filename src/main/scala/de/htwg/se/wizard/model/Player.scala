package de.htwg.se.wizard.model

case class Player (name: String, hand: List[Card] = List[Card]()) {

    override def toString:String = name

    def showHand(): String = {
        var string = ""
        for (x <- 1 to hand.size) {
            string += x + " = " + hand(x-1) + "; "
        }
        string
    }

    def playCard(card:Card) = Player(name, hand diff List(card))
}

package de.htwg.se.wizard.model

case class Player (name: String = "unkown", hand: List[Card] = List[Card]()) {

    override def toString:String = name

    def showHand(): String = {
        var string = ""
        for (x <- 1 to hand.size) {
            string += x + " = " + hand(x-1) + "; "
        }
        string
    }

    def playCard(card:Card):Player = Player(name, hand diff List(card))
}


class Builder() {
    var name: String = "unkown"
    var hand: List[Card] = List[Card]()

    def with_name(name: String): Builder = {
        this.name = name
        this
    }

    def with_hand(hand : List[Card]) : Builder = {
        this.hand = hand
        this
    }

    def build(): Player = {
        Player(name, hand)
    }
}


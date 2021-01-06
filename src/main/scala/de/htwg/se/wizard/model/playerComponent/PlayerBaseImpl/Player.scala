package de.htwg.se.wizard.model.playerComponent.PlayerBaseImpl

import com.google.inject.Inject
import de.htwg.se.wizard.model.cardsComponent.Card
import de.htwg.se.wizard.model.playerComponent.PlayerInterface

case class Player (name: String = "unknown", hand: List[Card] = List[Card]()) extends PlayerInterface {

    override def toString:String = name

    def showHand(): String = {
        var string = ""
        for (x <- 1 to hand.size) {
            string += x + " = " + hand(x-1) + "; "
        }
        string
    }

    def playCard(card:Card):Player = Player(name, hand diff List(card))

    override def getHand: List[Card] = hand

    override def getName: String = name
}


class Builder() {
    var name: String = "unknown"
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


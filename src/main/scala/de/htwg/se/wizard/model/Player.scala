package de.htwg.se.wizard.model

case class Player (name: String, hand: List[Card]) {

    override def toString:String =name

    def playCard(card:Card) = Player(name, hand diff List(card))
}

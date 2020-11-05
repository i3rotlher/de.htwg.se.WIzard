package de.htwg.se.wizard.model

case class Card(num: Int, colour: String) {
  override def toString: String = {
    "Card: %d, %s".format(num, colour)
  }
}

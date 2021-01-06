package de.htwg.se.wizard.model.playerComponent

import de.htwg.se.wizard.model.cardsComponent.Card

trait PlayerInterface {
  def showHand(): String
  def playCard(card:Card):PlayerInterface
  def getHand: List[Card]
  def getName: String
}

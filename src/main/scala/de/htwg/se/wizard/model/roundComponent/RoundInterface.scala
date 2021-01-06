package de.htwg.se.wizard.model.roundComponent

import de.htwg.se.wizard.model.roundComponent.RoundBaseImpl.Round

trait RoundInterface {
  def madeTricks(list: Iterable[Int]): Round
}

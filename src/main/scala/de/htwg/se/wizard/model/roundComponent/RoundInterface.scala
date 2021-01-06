package de.htwg.se.wizard.model.roundComponent

trait RoundInterface {
  def madeTricks(list: Iterable[Int]): Round
}

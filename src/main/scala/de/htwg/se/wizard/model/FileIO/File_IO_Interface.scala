package de.htwg.se.wizard.model.FileIO
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.Gamestate

trait File_IO_Interface {
  def load: Gamestate
  def save(state: Gamestate):Unit
}

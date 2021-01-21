package de.htwg.se.wizard.control.controllerBaseImpl

import de.htwg.se.wizard.model.playerComponent.PlayerBaseImpl.Player
import de.htwg.se.wizard.util.Command

class UndoPlayerNameCommand(player_name: String, controller: Controller) extends Command {

  override def doStep(): Unit = controller.game = controller.game.create_player(player_name)

  override def undoStep(): Unit = controller.game = controller.game.reset_player()

  override def redoStep(): Unit = controller.create_player(player_name)
}
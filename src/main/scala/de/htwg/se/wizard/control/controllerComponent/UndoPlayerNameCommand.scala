package de.htwg.se.wizard.control.controllerComponent

import de.htwg.se.wizard.model.playerComponent.Player
import de.htwg.se.wizard.util.Command

class UndoPlayerNameCommand(player_name: String, controller: Controller) extends Command {

  override def doStep(): Unit = controller.game = controller.game.create_player(player_name)

  override def undoStep(): Unit = controller.game = controller.game.copy(players = controller.game.players.updated(controller.active_player_idx()-1 ,Player()), active_Player_idx = controller.active_player_idx()-1)

  override def redoStep(): Unit = controller.create_player(player_name)
}
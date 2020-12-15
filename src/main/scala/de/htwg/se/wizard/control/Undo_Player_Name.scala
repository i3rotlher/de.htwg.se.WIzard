package de.htwg.se.wizard.control
import de.htwg.se.wizard.model.Player

trait Command {
  def doStep():Unit
  def undoStep():Unit
  def redoStep():Unit
}


class Undo_Player_Name(player_name: String, controller: Controller) extends Command {

  override def doStep(): Unit = controller.game = controller.game.create_player(player_name)

  override def undoStep(): Unit = controller.game = controller.game.copy(players = controller.game.players.updated(controller.active_player_idx()-1 ,Player()), active_Player_idx = controller.active_player_idx()-1)

  override def redoStep(): Unit = controller.create_player(player_name)
}

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    command.doStep()
  }

  def undoStep(): Unit = {
    undoStack match {
      case Nil =>
      case head::stack =>
        head.undoStep()
        undoStack = stack
        redoStack = head::redoStack
    }
  }

  def redoStep(): Unit = {
    redoStack match {
      case Nil =>
      case head::stack =>
        head.redoStep()
        redoStack = stack
        undoStack = head::undoStack
    }
  }
}
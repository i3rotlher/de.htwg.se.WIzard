package de.htwg.se.wizard
import com.google.inject.Guice
import de.htwg.se.wizard.aview.TUI
import de.htwg.se.wizard.aview.gui.SwingGUI
import de.htwg.se.wizard.control.controllerBaseImpl.{Controller, game_over, game_started}

//comment

object Wizard {

  val injector = Guice.createInjector(new WizardModule)
  val controller = injector.getInstance(classOf[Controller])
  val tui = new TUI(controller)
  val gui = new SwingGUI(controller)

  def main(args: Array[String]): Unit = {
    controller.publish(new game_started)
    do {
      val input = scala.io.StdIn.readLine()
      tui.processInput(input)
    } while (!tui.state.isInstanceOf[game_over])
  }

}


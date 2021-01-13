package de.htwg.se.wizard

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.wizard.control._
import de.htwg.se.wizard.control.controllerBaseImpl.Controller
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.Gamestate
import de.htwg.se.wizard.model.gamestateComponent.GamestateInterface


class WizardModule extends AbstractModule with ScalaModule {
  override def configure() = {
    bind[GamestateInterface].toInstance(Gamestate())
    bind[ControllerInteface].toInstance(Controller(Gamestate()))
  }
}

//package de.htwg.se.wizard
//
//import com.google.inject.AbstractModule
//import de.htwg.se.wizard.control._
//import de.htwg.se.wizard.model.gamestateComponent._
//import de.htwg.se.wizard.model.playerComponent._
//import net.codingwell.scalaguice.ScalaModule
//
//class WizardModule extends AbstractModule with ScalaModule {
//  override def configure() = {
//    bind[PlayerInterface].to[PlayerBaseImpl.Player]
//    bind[ControllerInteface].to[controllerBaseImpl.Controller]
//    bind[GamestateInterface].to[GamestateBaseImpl.Gamestate]
//  }
//}

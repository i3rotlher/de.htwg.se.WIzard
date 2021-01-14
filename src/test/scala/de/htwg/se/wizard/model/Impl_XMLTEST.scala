package de.htwg.se.wizard.model
import de.htwg.se.wizard.model.FileIO.XML.Impl_XML
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.Gamestate
import de.htwg.se.wizard.model.gamestateComponent.GamestateInterface
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
class Impl_XMLTEST extends AnyWordSpec {
  "an XML" when{
    "created" should{
      val xml = new Impl_XML()
      var game: GamestateInterface = Gamestate().set_player_amount(3)
      game = game.create_player("Max")
      game = game.create_player("Niclas")
      game = game.create_player("Prof")
      val state = Gamestate(players = game.getPlayers)
      print(xml.players_toXML(state.players))
    }
  }
}

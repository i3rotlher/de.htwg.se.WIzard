package de.htwg.se.wizard.model.FileIO.JSON

import com.google.inject.Guice
import de.htwg.se.wizard.WizardModule
import de.htwg.se.wizard.model.FileIO.File_IO_Interface
import de.htwg.se.wizard.model.cardsComponent.{Card, Card_with_value}
import de.htwg.se.wizard.model.gamestateComponent.GamestateBaseImpl.Round
import de.htwg.se.wizard.model.gamestateComponent.GamestateInterface
import de.htwg.se.wizard.model.playerComponent.PlayerBaseImpl.Player
import play.api.libs.json._

import scala.io.Source
import scala.swing.event.Event

case class Impl_JSON() extends File_IO_Interface {

  override def load : (GamestateInterface, String) = {

    val injector = Guice.createInjector(new WizardModule)
    val source: String = Source.fromFile("gamestate.json").getLines.mkString
    val json = Json.parse(source)
    val round_number = (json \ "round_number").get.toString.toInt
    val state = (json \ "state").get.toString()
    val trump_card = new Card_with_value((json("trump_card") \ "value").get.toString().toInt, (json("trump_card") \ "color").get.toString())
    val Mini_starter_idx = (json \ "mini_starter_idx").get.toString.toInt
    val mini_played_counter = (json \ "mini_played_counter").get.toString.toInt
    val active_player_idx = (json \ "active_player_idx").get.toString().toInt
    val serve_card = new Card_with_value((json("serve_card") \ "value").get.toString().toInt, (json("serve_card") \ "color").get.toString())


    val players = Json.parse((json \ "players").get.toString()).as[List[JsValue]]
    var player_list = List[Player]()
    for (p <- players.indices) {
      val name = (players(p)\ "name").get.toString()
      var hand = List[Card]()
      val hand_p = Json.parse("["+players(p).toString()+"]").as[List[JsValue]]
      for (card <- 1 until hand_p.length) {
        hand = hand.appended(new Card_with_value((hand_p(card) \ "value").get.toString().toInt, (hand_p(card) \ "color").get.toString()))
      }
      player_list = player_list.appended(Player(name, hand))
    }


    val gametable = (json \ "game_table")
    val made_tricks = (json \ "made_tricks").get
    val played_cards = (json \ "playedCards").get

//    (new Gamestate(players = player_list, game_table = gametable, round_number = round_number, trump_Card = trump_card,
//      serve_card = serve_card, made_tricks = made_tricks, playedCards = played_cards, mini_starter_idx = Mini_starter_idx,
//      mini_played_counter = mini_played_counter, active_Player_idx = active_player_idx),state)
    null
  }

  implicit val readPlayerList = new Reads[List[Player]] {
    override def reads(json: JsValue) = {
      null
    }
  }

  override def save(gamestate: GamestateInterface, state: Event): Unit = {
    import java.io._
    val print_writer = new PrintWriter(new File("gamestate.json"))
    print_writer.write(Json.prettyPrint(gameStateToJson(gamestate, state)))
    print_writer.close()
  }

  implicit val cardWrites = new Writes[Card] {
    def writes(card: Card) = Json.obj(
      "value" -> card.num,
      "color" -> card.colour
    )
  }

  implicit val playerWrites = new Writes[Player] {
    def writes(player: Player) = { Json.obj(
        "name" -> player.name,
        "hand" -> Json.toJson(
          for (card <- player.hand) yield Json.toJson(card)
        )
      )
    }
  }

  implicit val roundWrites = new Writes[Round] {
    def writes(round: Round) = {
      Json.obj(
        "guessed_tricks" -> Json.toJson(round.guessed_tricks),
        "results" -> Json.toJson(round.results)
      )
    }
  }

  def gameStateToJson(game: GamestateInterface, state: Event) = {
      Json.obj(
        "state" -> state.getClass.toString.replace("class de.htwg.se.wizard.control.controllerBaseImpl.", ""),
          "game_table" -> game.getGame_table,
          "players" -> game.getPlayers,
          "round_number" -> game.getRound_number,
          "active_player_idx" -> game.getActive_player_idx,
          "trump_card" -> game.getTrump_card,
          "serve_card" -> game.getServe_card,
          "made_tricks" -> game.getMade_tricks,
          "playedCards" -> game.getPlayedCards,
          "mini_starter_idx" -> game.getMini_starter,
          "mini_played_counter" -> game.getMini_played_counter
      )
    }

}

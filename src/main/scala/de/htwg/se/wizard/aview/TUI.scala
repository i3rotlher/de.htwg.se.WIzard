package de.htwg.se.wizard.aview

import de.htwg.se.wizard.model.{Card, Cards, Gamestate, Player}

import scala.util.{Failure, Success, Try}

case class TUI() {

  def next_player_Card(player: Player): Card = {
    print(player.name + " du bist dran!")
    print(player.showHand())
    print("Welche Karte soll gespielt werden ?")
    var fail = true
    var playcard = getNumber()
    while (fail) {
      if (!(playcard >= 1 && playcard <= player.hand.size)) {
        print("Bitte gebe eine gültige Karte ein!")
        print("Welche Karte soll gespielt werden ?")
        print(player.showHand())
        playcard = getNumber()
      } else {
        fail = false
      }
    }
    player.hand(playcard)
  }

  def getNumber(): Int = {
    var fail = true
    var input = toInt(scala.io.StdIn.readLine())
    while (fail) {
      var input = toInt(scala.io.StdIn.readLine())
      input match {
        case Failure(f) => print("Bitte gebe eine gültige Zahl ein!")
          input = toInt(scala.io.StdIn.readLine())
        case Success(s) => fail = false
      }
    }
    input.get
  }

  def toInt(s: String): Try[Int] = Try(Integer.parseInt(s.trim))

  def createPlayers(): Gamestate = {
    val game = Gamestate()
    // abfrage nach spieleranzahl
    print("Wie viele Spieler wollen spielen ? [3,4,5 oder 6]")
    var playercount = scala.io.StdIn.readLine()
    while (!List("3", "4", "5", "6").contains(playercount)) {
      print("Es können nur 3,4,5 oder 6 Spieler spielen!")
      playercount = scala.io.StdIn.readLine()
    }

    var players = List[Player]()

    for (x <- 1 to getNumber()) {
      print("Bitte gib deinen Namen ein Spieler " + x + ":")
      players = players.appended(Player(scala.io.StdIn.readLine()))
    }
    game.init_Gamestate(players)
  }

  def get_guesses(game: Gamestate, round_counter: Int): List[Int] = {
    var to_win = Array[Int](game.players.size)
    for (i <- game.players) {
      val active_Player = ((round_counter - 1) % game.players.size) + 1
      printf("\n\n\n\n\n")
      game.players(active_Player).showHand()
      println(active_Player.toString + " wieviele Stiche wirst du machen?")
      to_win(active_Player) = getNumber()
    }
    to_win.toList
  }

}

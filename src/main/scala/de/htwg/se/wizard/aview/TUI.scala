package de.htwg.se.wizard.aview

import de.htwg.se.wizard.model.{Card, Gamestate}

case class TUI {

    def processInput(input : String) : Gamestate = {
        //inputs verarbeite
            //karte ausspielen die mit einem Inut (IDX) 0-9 gewählt wurde

    }

    def nextplayerCard():Card={
        //"Spieler x wähle deine karte"
        //zeigt Spieler seine Karten
        //isPlayable(Karte) -> neue karte auswaehlen ?
        //PlayCard
    }

    def createPlayers():Gamestate = {
        // abfrage nach spieleranzahl
        // List spieler x
        // for spieranzahl
            // x += Spieler(namensabfrage)
        //return gamestate.init(x)
    }
}

package de.htwg.se.wizard.model

case class Gamestate {

    val players = List[Player]
    val gametable = List[Round]

//  am rundenende aufrufen um auswertung zu mahen und in den "Block" einzufuegen
    def round_finished(round : Round) : Gamestate = {
        // round madeTricks aufrufen
        // copy mit neuen gametable (gametable+=round)
        // neuer rundenbeginner
    }

    def init_Gamestate(players : List[Player]): Gamestate = {
        //Spieler anlegen
        //copy
    }

    // erst aufrufen nach check
    def played_Card(player : Player, played : Card) : Gamestate {
        // idx aus playerliste
        // calcwinner aufrufen
    }

    def play_mini() = {
        //neuer mini beginner
    }

    def isOver() : Boolean = {
       gametable.size == (60/players.size)
    }

}

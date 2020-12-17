package de.htwg.se.wizard.model

import de.htwg.se.wizard.control._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class StateTest extends AnyWordSpec {{
  "all state tests" should {
    "be callable classes" in {
      new name_ok
      new set_Wizard_trump
      new game_started
      new get_Amount
      new player_create
      new round_started
      new start_round
      new Wizard_trump
      new next_guess
      new next_player_card
      new round_over
      new card_not_playable
      new guesses_set
      new mini_over
      new game_over

    }
  }
}
}

package de.htwg.se.wizard.model

import de.htwg.se.wizard.Wizard
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class WizardTest extends AnyWordSpec {

    "wizard" should {
      "return" in {
        Wizard.main(null) should be()
      }
    }
}
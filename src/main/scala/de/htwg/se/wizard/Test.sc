import de.htwg.se.wizard.aview.gui.Table_panel
import de.htwg.se.wizard.model.{Cards, Gamestate}

import scala.swing.Frame

val frame = new Frame

frame.contents = new Table_panel(Gamestate())

frame.visible = true

package de.htwg.se.wizard.aview.gui

import de.htwg.se.wizard.model.Card
import javax.swing.ImageIcon
import scala.swing.Label

case class Card_panel(card: Card) extends Label {
    var imageIcon = new ImageIcon(card_path.get_card_face_path(card))
    val image = imageIcon.getImage()
    val newimg = image.getScaledInstance(200, (703 * 0.441501104).toInt, java.awt.Image.SCALE_SMOOTH)
    iconTextGap = 2
    icon = new ImageIcon(newimg)
}

object card_path {
  def get_card_face_path(card: Card): String = {
    if (card.colour.contains("none")) {
      return "C:\\Users\\maxek\\Desktop\\AIN\\Semester 3\\Software Engeneering\\de.htwg.se.Wizard\\Card_faces\\" + (card.colour.substring(card.colour.indexOf("(") + 1, card.colour.indexOf("(") + 2) + card.num) + ".png"
    }
    "C:\\Users\\maxek\\Desktop\\AIN\\Semester 3\\Software Engeneering\\de.htwg.se.Wizard\\Card_faces\\" + (card.colour.substring(0, 1) + card.num) + ".png"
  }
}
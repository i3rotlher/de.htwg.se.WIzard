package de.htwg.se.wizard.model

case class Card(num: Int, colour: String) {
  override def toString: String = {
    var name = num.toString
    if (name == "0") name = "Narr"
    else if (name == "14") name = "Zauberer"
    "(%s, %s)".format(name, colour)
  }

}

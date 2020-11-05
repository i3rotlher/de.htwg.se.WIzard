//import funktioniert nicht

case class Card(num: Int, colour: String) {
  override def toString: String = {
    "Card: %d, %s".format(num, colour)
  }
}

val all_cards = Array(
  Card(0, "green"),
  Card(1, "green")
)

print(all_cards(0))
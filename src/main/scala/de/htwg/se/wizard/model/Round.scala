package de.htwg.se.wizard.model


case class Round(guessed_tricks: List[Int], made_tricks : List[Int] = List.empty, results : List[Int] = List.empty) {

  def madeTicks(list: List[Int]): Round = {
    var x = List[Int]()
    guessed_tricks.lazyZip(list)foreach{ (guessed , made) =>
      if (guessed == made)
        x = made*10 + 20 :: x
      else
        x = (made-guessed).abs*(-10) :: x
    }
    Round(guessed_tricks, made_tricks = list, results = x.reverse)
  }

  override def toString(): String = ("Tricks guessed " + guessed_tricks + "; Tricks made " + made_tricks + "; Results " + results)

}

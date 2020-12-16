package de.htwg.se.wizard.model

import java.awt.Image
import java.io.File
import java.nio.file.Path

import javax.imageio.ImageIO

import scala.swing.Image

abstract case class Card(num: Int, colour: String) {
  def get_String: String
  override def toString:String = get_String
}
class Card_with_value(num: Int, colour: String) extends Card(num, colour) {
    override def get_String: String = "(%s, %s)".format(num.toString, colour)
  }
class Card_wizard(num: Int, colour: String) extends Card(num, colour) {
    override def get_String: String = "(%s, %s)".format("Zauberer", colour)
  }
class Card_fool(num: Int, colour: String) extends Card(num, colour){
    override def get_String: String = "(%s, %s)".format("Narr", colour)
  }


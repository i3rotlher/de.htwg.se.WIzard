package de.htwg.se.wizard.util

trait Observer {
  def update(status: String): Unit
}

class Observable {
  var subscriber: Vector[Observer] = Vector()

  def add(to_add: Observer): Unit = subscriber = subscriber :+ to_add

  def remove(to_remove: Observer): Unit = subscriber = subscriber.filterNot(subscriptions => subscriptions == to_remove)

  def notify_Observer(status: String): Unit = subscriber.foreach(observer => observer.update(status))
}

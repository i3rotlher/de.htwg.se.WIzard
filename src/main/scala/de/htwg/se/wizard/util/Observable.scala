//package de.htwg.se.wizard.util
//import de.htwg.se.wizard.control.State
//
//trait Observer {
//  def update(status: State.Value): Boolean
//}
//
//class Observable {
//
//  var subscriber: Vector[Observer] = Vector()
//  def add(to_add: Observer): Unit = subscriber = subscriber :+ to_add
//  def remove(to_remove: Observer): Unit = subscriber = subscriber.filterNot(subscriptions => subscriptions == to_remove)
//  def notify_Observer(status: State.Value): Unit = subscriber.foreach(observer => observer.update(status: State.Value))
//}

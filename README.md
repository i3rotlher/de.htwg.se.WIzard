# Wizard
This is a card game. The goal is to predict the tricks you are going to make each round. The original game by amigo can be found here:
https://www.amigo-spiele.de/spiel/wizard

[![Build Status](https://travis-ci.org/i3rotlher/de.htwg.se.Wizard.svg?branch=master)](https://travis-ci.org/i3rotlher/de.htwg.se.Wizard)
[![Coverage Status](https://coveralls.io/repos/github/i3rotlher/de.htwg.se.Wizard/badge.svg?branch=master)](https://coveralls.io/github/i3rotlher/de.htwg.se.Wizard?branch=master)

## Rules

The objective of the game is to bid correctly on the number of tricks that a player will take in the subsequent round of play. Points are awarded for a correct bid and subtracted for an incorrect bid. The player with most points after all rounds have been played is the winner. The game is played in a number of rounds from 10 to 20, depending on the number of players and each round consists of three stages: Dealing, Bidding, and Playing.

In the first round every player gets one card. In the subsequent rounds the number of cards is increased by one until all cards are distributed. That means that three players play 20 rounds, four players 15 rounds, five players 12 rounds and six players 10 rounds. The top card of the remaining cards is turned over to determine the trump suit. If there are no cards left or a jester is turned, there is no trump suit, and only the wizards are trump. If a wizard is turned, the dealer picks a trump suit.

After looking at their cards, starting with the player to the dealer's left, each player states how many tricks he believes he will take, from zero to the number of cards dealt. This is recorded on a score pad.

The player to the left of the dealer plays a card, and then the others follow clockwise. If a card other than a wizard or jester is played, the players have to follow suit, but it is possible to play a jester or wizard although the player has the desired suit. The Wizard beats all other cards but the first one in a trick beats all others. The jester is beaten by all others, but if all cards in a trick are jesters the first one beats the others. If a jester is played as the first card the first suit card decides which suit has to be followed. If a wizard is played as the first card every player is free to play what they want regardless of the others. If the first card is a Jester and the second a Wizard, then the Wizard rule takes precedence and players are not required to follow suit.
At the end of each round, each player is given a score based on his performance. For predicting the number of tricks taken correctly, a player receives 20 points plus 10 points for each trick taken. For predicting the number of tricks taken incorrectly, a player loses 10 points for each trick over or under.
[https://en.wikipedia.org/w/index.php?title=Wizard_(card_game)&oldid=975588038]

## The GraphicalUserInterface
![the gui](pic/gui.png)

_________________
# How are you today - Gruppe 13
Bei unserem Projekt dachte wir uns das wir nicht nur auf die älteren User zielen sollten, sondern das sehr viele Studenten*innen unter Depression oder Einsamkeit leiden, vorallem zu Pandemie Zeiten. Deshalb ist unsere Persona eine junge Studentin.
## Persona Lisa Anna Marie
![abgabe2.png](https://www.dropbox.com/s/0uxumbbispqrxdx/abgabe2.png?dl=0&raw=1)
### UX Screen 1
![dasdpng.png](https://www.dropbox.com/s/sl3svdm78hmbi72/dasdpng.png?dl=0&raw=1)
Als Home Screen wollten wir ein simples, nicht überforderndes Design wählen. Lisa Marie möchte keinen großen Aufwand betreiben um mitzuteilen wie ihre Gefühlslage heute ist. Darum besteht der Screen auch nur aus 5 Buttons mit den jeweiligen Gefühlslagen welche Sie nach anklicken zu der Eintragserstellungsseite weiter leiten. Es befindet sich des weiteren ein unauffälliger Menü Button in der oberen linken Ecke bei dem sich dann ein Menü seitlich links öffnen würde in dem man bestimmt Funktionen wie Kontakt änder oder Verlauf löschen etc. ausüben könnte, um es aber simpel zu halten zeigen wir daher nur den Menü Button zu beginn an. Als Hintergrundfarbe haben wir darauf geachtet eine neutral bis eher positive Farbe zu wählen und entschieden uns daher für ein sehr blasses Grün. Es gibt noch einen weiteren Knopf der einen zum verlauf der letzten Einträge bringt (UX Screen 2). Dadurch erhalten wir eine sehr hohe Usabilitiy und auch User Experience.

### UX Screen 2
![sad.png](https://www.dropbox.com/s/x3njh9luvxuu5me/sad.png?dl=0&raw=1)

Die User sollen in der Lage sein ihre Vergangenen Bewertungen ihrer Gefühllage zu sehen. Dabei ist es wichtig das die Begründung der Gefühlslage hinterlegt ist, damit die User nachvolziehen können wie es dazu kam und was ihnen am Ende geholfen hat. Dadurch kann man Vorkerungen treffen und bestimmte Sad Feeling moment umgehen oder Happy momente öfter Triggern. Die Idee wird auch in der Psychologie sehr oft verwendet wie z.B. ein Depressions Tracker. Unser Ziel war es dieses Verfahren so einfach wir möglich zu halten ohne den User zu lange zu beschäftigen. Max 5 min pro Tag muss ein User in der App verwenden um die volle Funktionalität der App zu erhalten. Wir erhoffen und mit der History- Liste die wir in der App haben, für mehr Selbstreflexion bei dem User zu erschaffen. Das Zurück-Button ist oben rechts, damit man während dem runter scrollen nicht ausversehen drauf kommt. Somit schaffen wir frustfreies zurück scrollen ab, falls man ausversehen drauf kommen würde. Die Liste soll ausserdem alle Einträge abspeichern, da jeder Tag wichtig und informativ für unsere User ist. 
## Links und Literatur
https://moodle.htwg-konstanz.de/moodle/pluginfile.php/347504/mod_resource/content/2/PersonaTemplate.pdf
https://www.marketinginstitut.biz/blog/personas-entwickeln/
https://unsplash.com/photos/7KHCNCddn2U
https://www.figma.com/community/file/880534892514982400
Slides - Mobile Design PatternsDatei by Prof. Dr. Ralf Schimkat Sommersemester 2021

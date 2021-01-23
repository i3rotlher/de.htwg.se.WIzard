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

package kowloon.core.model.phase

import kowloon.core.model.hand.Hand

enum GamePhase:
    case Initializing, InProgress, Completed

case class PlayerState(
                        hand: Hand,
                        score: Int,
                        riichiStatus: RiichiStatus
                      )

enum RiichiStatus:
    case NotDeclared
    case Declared(remainingTiles: Int)
    case Completed
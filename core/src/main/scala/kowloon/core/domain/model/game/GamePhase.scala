package kowloon.core.domain.model.game

sealed trait GamePhase
object GamePhase {
  case object Initializing extends GamePhase
  case object InProgress extends GamePhase
  case object Completed extends GamePhase
}

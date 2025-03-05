package kowloon.core.domain.model.player

sealed trait RiichiStatus
object RiichiStatus {
  case object NotDeclared extends RiichiStatus
  case class Declared(remainingTurns: Int) extends RiichiStatus
}

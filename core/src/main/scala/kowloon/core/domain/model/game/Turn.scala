package kowloon.core.domain.model.game

case class Turn(value: Int) extends AnyVal {
  def next: Turn = Turn(value + 1)
}

object Turn {
  def initial: Turn = Turn(1)
}

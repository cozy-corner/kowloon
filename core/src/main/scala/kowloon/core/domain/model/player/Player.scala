package kowloon.core.domain.model.player

import java.util.UUID

class Player(val id: PlayerId, val name: String) {
  override def toString: String = s"Player($id, $name)"
}

class PlayerId(val value: UUID) {
  override def toString: String = s"PlayerId($value)"
}
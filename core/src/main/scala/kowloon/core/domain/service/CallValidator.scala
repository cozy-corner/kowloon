package kowloon.core.domain.service

import kowloon.core.domain.model.game.DiscardPile
import kowloon.core.domain.model.player.Player
import kowloon.core.domain.model.tile.Tile

trait CallValidator {
  def canCall(
      targetTile: Tile,
      player: Player,
      discardPile: DiscardPile
  ): Boolean
}

object PonValidator extends CallValidator {
  override def canCall(
      targetTile: Tile,
      player: Player,
      discardPile: DiscardPile
  ): Boolean = false
}

object ChiiValidator extends CallValidator {
  override def canCall(
      targetTile: Tile,
      player: Player,
      discardPile: DiscardPile
  ): Boolean = false
}

object KanValidator extends CallValidator {
  override def canCall(
      targetTile: Tile,
      player: Player,
      discardPile: DiscardPile
  ): Boolean = false
}

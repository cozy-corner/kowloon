package kowloon.core.domain.service

import kowloon.core.domain.model.game.DiscardPile
import kowloon.core.domain.model.player.Player
import kowloon.core.domain.model.tile.Tile

// TODO 抽象化する
class CallValidator {
  def canPon(
      targetTile: Tile,
      player: Player,
      discardPile: DiscardPile
  ): Boolean = ???

  def canChii(
      targetTile: Tile,
      player: Player,
      discardPile: DiscardPile
  ): Boolean = ???

  def canKan(
      targetTile: Tile,
      player: Player,
      discardPile: DiscardPile
  ): Boolean = ???
}

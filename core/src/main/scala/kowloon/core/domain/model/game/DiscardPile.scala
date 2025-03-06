package kowloon.core.domain.model.game

import kowloon.core.domain.model.player.PlayerId
import kowloon.core.domain.model.tile.Tile

// 河
final case class DiscardPile(
    discards: Vector[DiscardedTile],
    riichiSticks: Int
) {
  def isFuriten(player: PlayerId): Boolean = ???
}

// 捨牌
case class DiscardedTile(
    tile: Tile,
    player: PlayerId,
    turn: Int,
    isTsumogiri: Boolean,
    isRiichi: Boolean
)

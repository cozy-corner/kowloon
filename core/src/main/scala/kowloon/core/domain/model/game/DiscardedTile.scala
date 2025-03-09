package kowloon.core.domain.model.game

import kowloon.core.domain.model.tile.Tile
import kowloon.core.domain.model.player.PlayerId

final case class DiscardedTile(
    tile: Tile,
    player: PlayerId,
    turn: Int, // 何巡目か（1開始）
    isTsumogiri: Boolean
)

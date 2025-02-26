package kowloon.core.model.meld

import kowloon.core.model.player.PlayerId
import kowloon.core.model.tile.Tile

enum Meld {
  // 通常の副露
  case Pon(tile: Tile, stolenFrom: PlayerId)
  case Chi(tiles: (Tile.Suit, Tile.Suit, Tile.Suit), stolenFrom: PlayerId)
  case OpenKan(tile: Tile, stolenFrom: PlayerId) // 明槓

  // 暗槓/加槓
  case ClosedKan(tile: Tile) // 暗槓
  case AddedKan(tile: Tile) // 加槓（既存の刻子から）

  // リーチ後の暗槓（特別扱い）
  case RiichiClosedKan(tile: Tile)
}

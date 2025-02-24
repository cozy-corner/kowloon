package kowloon.core.model.hand

import kowloon.core.model.tile.{SuitType, Tile}

// 手牌と面子
case class Hand(
                 concealed: List[Tile], // 手牌
                 revealed: List[Meld], // 上り
                 drawnTile: Option[Tile] // ツモ牌
               )

// 面子
enum Meld:
    case Sequence(suit: SuitType, start: Int)  // 順子
    case Triplet(tile: Tile)                   // 刻子
    case Quad(tile: Tile)                      // 槓子
    case Pair(tile: Tile)                      // 雀頭
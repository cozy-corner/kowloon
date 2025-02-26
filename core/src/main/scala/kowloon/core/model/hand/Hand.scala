package kowloon.core.model.hand

import kowloon.core.model.meld.Meld
import kowloon.core.model.tile.{SuitType, Tile}

case class Hand(
    concealed: Vector[Tile], // 伏せられた牌（Vectorでパフォーマンス確保）
    openMelds: List[Meld], // 副露した面子
    lastDraw: Option[Tile] // 最新のツモ牌
) {
  def canDeclareRiichi: Boolean =
    concealed.size == 13 && openMelds.isEmpty

  def isTenpai: Boolean = ??? // テンパイ判定ロジック
}

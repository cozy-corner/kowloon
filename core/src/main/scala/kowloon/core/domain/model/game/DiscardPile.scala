package kowloon.core.domain.model.game

import kowloon.core.domain.model.player.PlayerId
import kowloon.core.domain.model.tile.Tile

final case class DiscardPile private (
    discards: Vector[DiscardedTile]
) {

  def addDiscard(
      tile: Tile,
      player: PlayerId,
      isTsumogiri: Boolean
  ): DiscardPile = {
    val newDiscard = DiscardedTile(
      tile = tile,
      player = player,
      turn = discards.size + 1,
      isTsumogiri = isTsumogiri
    )
    copy(discards = discards :+ newDiscard)
  }

  /** 直近N巡の捨て牌を取得
    * @param turns
    *   遡る巡数
    *
    * UI用を想定
    */
  def recentDiscards(turns: Int): Vector[DiscardedTile] = {
    // TODO: 有効な範囲チェック
    discards.takeRight(turns)
  }

  def allDiscards: Vector[DiscardedTile] = discards // フリテン判定用

  def lastDiscard: Option[DiscardedTile] = discards.lastOption // 鳴き判定用
}

object DiscardPile {
  def empty: DiscardPile = DiscardPile(Vector.empty)
}

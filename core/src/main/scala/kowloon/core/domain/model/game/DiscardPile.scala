package kowloon.core.domain.model.game

import kowloon.core.domain.model.game.DiscardPile.{DiscardError, DuplicateRiichi, MaxRiichiExceeded}
import kowloon.core.domain.model.player.PlayerId
import kowloon.core.domain.model.tile.Tile

final case class DiscardPile(
    discards: Vector[DiscardedTile] = Vector.empty,
    riichiSticks: Int = 0
) {

  private def validateRiichiDiscard(discarded: DiscardedTile): Either[DiscardError, Unit] =
    if (discarded.isRiichi && riichiSticks >= 4)
      Left(MaxRiichiExceeded)
    else if (discarded.isRiichi && discards.exists(_.isRiichi))
      Left(DuplicateRiichi)
    else
      Right(())

  private def getDiscardsBy(player: PlayerId): Vector[DiscardedTile] =
    discards.filter(_.player == player)

  def addDiscard(discarded: DiscardedTile): Either[DiscardError, DiscardPile] =
    validateRiichiDiscard(discarded).map { _ =>
      val newSticks = if (discarded.isRiichi) riichiSticks + 1 else riichiSticks
      copy(discards = discards :+ discarded, riichiSticks = newSticks)
    }

  def isFuriten(player: PlayerId, winningTiles: Set[Tile]): Boolean =
    getDiscardsBy(player).exists(d => winningTiles.contains(d.tile))
}

object DiscardPile {
  def initial: DiscardPile = DiscardPile(
    discards = Vector.empty,
    riichiSticks = 0
  )

  sealed trait DiscardError

  case object MaxRiichiExceeded extends DiscardError

  case object DuplicateRiichi extends DiscardError

  case class InvalidTurnOrder(expected: Turn, actual: Turn) extends DiscardError
}

// 捨牌の基本構造
case class DiscardedTile(
    tile: Tile,
    player: PlayerId,
    turn: Turn, // 局内の連続した行動順序
    isTsumogiri: Boolean,
    isRiichi: Boolean
)
object DiscardedTile {
  def create(
      tile: Tile,
      player: PlayerId,
      turn: Turn,
      isTsumogiri: Boolean,
      isRiichi: Boolean
  ): Either[String, DiscardedTile] = ???
}

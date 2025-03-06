package kowloon.core.domain.model.player

import kowloon.core.domain.model.tile.Tile

case class Hand(
    concealed: Vector[Tile],
    openMelds: Vector[Meld],
    drawnTile: Option[Tile]
) {
  def discard(tile: Tile): Either[String, Hand] = ???
}

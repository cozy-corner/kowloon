package kowloon.core.domain.model.hand

import kowloon.core.domain.model.tile.Tile
import kowloon.core.domain.model.hand.Meld

case class Hand(
    concealed: Vector[Tile],
    openMelds: Vector[Meld],
    drawnTile: Option[Tile]
) {
  def discard(tile: Tile): Either[String, Hand] = ???
}

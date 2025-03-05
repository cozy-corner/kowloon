package kowloon.core.domain.model.game

import kowloon.core.domain.model.tile.Tile

final case class Wall private (
    liveTiles: Vector[Tile],
    deadWall: Vector[Tile],
    doraRevealed: Int
) {
  def draw(): Option[(Tile, Wall)] = ???
}

object Wall {
  def initialize(shuffle: Vector[Tile] => Vector[Tile]): Wall = ???
}

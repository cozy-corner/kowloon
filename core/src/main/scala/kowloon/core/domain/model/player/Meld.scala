package kowloon.core.domain.model.player

import kowloon.core.domain.model.tile.Tile

sealed trait Meld
case class Sequence(tiles: Vector[Tile]) extends Meld
case class Triplet(tile: Tile) extends Meld

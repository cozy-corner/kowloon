package kowloon.core.domain.model.tile

sealed trait TileType
object TileType {
  case object Character extends TileType
  case object Bamboo extends TileType
  case object Dot extends TileType
  case object Wind extends TileType
  case object Dragon extends TileType
}

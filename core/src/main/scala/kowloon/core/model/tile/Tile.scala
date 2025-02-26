package kowloon.core.model.tile

sealed trait Tile {
  def isTerminal: Boolean = false
  def isHonor: Boolean = false
}

object Tile {
  case class Suit(suit: SuitType, number: 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9) extends Tile {
    override def isTerminal: Boolean = number == 1 || number == 9
  }

  sealed trait Honor extends Tile {
    override def isHonor: Boolean = true
  }

  case class Wind(wind: WindType) extends Honor
  case class Dragon(dragon: DragonType) extends Honor
}

enum SuitType:
  case Character, Bamboo, Dot

enum WindType:
  case East, South, West, North

enum DragonType:
  case Red, Green, White

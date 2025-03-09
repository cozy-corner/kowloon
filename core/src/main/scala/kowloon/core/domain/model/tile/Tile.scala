package kowloon.core.domain.model.tile

final case class Tile private (
    tileType: TileType,
    number: Option[Int] = None
) {
  require(
    (tileType, number) match {
      case (_: TileType.Suit, Some(n))                  => n >= 1 && n <= 9
      case (_: (TileType.Wind | TileType.Dragon), None) => true
      case _                                            => false
    },
    "牌の組み合わせが不正です"
  )
}

object Tile {
  def createSuit(suitType: TileType.Suit, number: Int): Either[String, Tile] =
    if (number >= 1 && number <= 9) Right(Tile(suitType, Some(number)))
    else Left("数牌の数字は1〜9でなければなりません")

  def createWind(wind: TileType.Wind): Tile =
    Tile(wind, None)

  def createDragon(dragon: TileType.Dragon): Tile =
    Tile(dragon, None)

  def isTerminal(tile: Tile): Boolean = tile match {
    case Tile(_: TileType.Suit, Some(1 | 9)) => true
    case _                                   => false
  }
}

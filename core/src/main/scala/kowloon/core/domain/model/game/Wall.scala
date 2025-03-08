package kowloon.core.domain.model.game

import kowloon.core.domain.model.tile.{Tile, TileType}

final case class Wall private (
    liveTiles: Vector[Tile],
    deadWall: Vector[Tile],
    doraIndicators: Vector[Tile]
) {
  // ツモ処理（TODO: 王牌からの補充ロジック）
  def draw(): Option[(Tile, Wall)] = liveTiles.headOption.map { tile =>
    val newWall = copy(liveTiles = liveTiles.tail)
    (tile, newWall)
  }

  // ドラ表示（TODO: 裏ドラ対応）
  def revealDora(): Either[WallError, (Tile, Wall)] =
    deadWall.headOption match {
      case Some(tile) =>
        val newDora = doraIndicators :+ tile
        val newDeadWall = deadWall.tail
        Right((tile, copy(deadWall = newDeadWall, doraIndicators = newDora)))
      case None =>
        Left(WallError.DoraNotFound)
    }
}

object Wall {
  private def assertValidTileCount(tiles: Vector[Tile]): Unit = {
    assert(tiles.size == 136, "牌の総数が136枚ではありません")
    assert(tiles.count(tile => tile.tileType.isInstanceOf[TileType.Suit]) == 108, "数牌の数が不正です")
    assert(tiles.count(tile => tile.tileType.isInstanceOf[TileType.Wind]) == 16, "風牌の数が不正です")
    assert(tiles.count(tile => tile.tileType.isInstanceOf[TileType.Dragon]) == 12, "三元牌の数が不正です")
  }

  def initialize(shuffle: Vector[Tile] => Vector[Tile]): Wall = {
    val allTiles = generateAllTiles()
    assertValidTileCount(allTiles)

    val shuffled = shuffle(allTiles)
    val (live, dead) = shuffled.splitAt(122)
    val (initialDora, remainingDead) = dead.splitAt(1)

    new Wall(live, remainingDead, initialDora)
  }

  private def generateAllTiles(): Vector[Tile] = {
    val suits = for {
      suitType <- Seq(
        (n: Int) => TileType.Suit.Character(n),
        (n: Int) => TileType.Suit.Bamboo(n),
        (n: Int) => TileType.Suit.Dot(n)
      )
      number <- 1 to 9
      _ <- 1 to 4
    } yield Tile
      .createSuit(suitType(number), number)
      .getOrElse(
        throw new AssertionError("数牌の生成に失敗しました")
      )

    val winds = Seq
      .fill(4)(
        Seq(
          Tile.createWind(TileType.Wind.East),
          Tile.createWind(TileType.Wind.South),
          Tile.createWind(TileType.Wind.West),
          Tile.createWind(TileType.Wind.North)
        )
      )
      .flatten

    val dragons = Seq
      .fill(4)(
        Seq(
          Tile.createDragon(TileType.Dragon.White),
          Tile.createDragon(TileType.Dragon.Green),
          Tile.createDragon(TileType.Dragon.Red)
        )
      )
      .flatten

    (suits ++ winds ++ dragons).toVector
  }
}

trait WallError {}

object WallError {
  case object DoraNotFound extends WallError

}

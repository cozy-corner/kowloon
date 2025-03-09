package kowloon.core.domain.model.game

import kowloon.core.domain.model.tile.{Tile, TileType}
import org.scalatest.funsuite.AnyFunSuiteLike

class WallTest extends AnyFunSuiteLike {

  test("Wall initialization should create a valid wall with correct counts") {
    // Initialize wall with identity function (no shuffle)
    val wall = Wall.initialize(identity)

    // Check initial structure
    assert(wall.liveTiles.length == 122)
    assert(wall.deadWall.length == 13)
    assert(wall.doraIndicators.length == 1)
  }

  test("Wall draw should return a tile and update the wall") {
    val wall = Wall.initialize(identity)
    val initialLiveTileCount = wall.liveTiles.length

    val drawResult = wall.draw()
    assert(drawResult.isDefined)

    val (drawnTile, newWall) = drawResult.get
    assert(newWall.liveTiles.length == initialLiveTileCount - 1)
    assert(newWall.liveTiles.count(_ == drawnTile) == 3) // 4 of each tile in the wall in mahjong
  }

  test("Wall should support multiple draws") {
    val wall = Wall.initialize(identity)

    // Draw 10 tiles
    val (_, wallAfter10Draws) = (1 to 10).foldLeft((Vector.empty[Tile], wall)) { case ((tiles, currentWall), _) =>
      currentWall.draw() match {
        case Some((tile, newWall)) => (tiles :+ tile, newWall)
        case None                  => fail("Draw should succeed but returned None")
      }
    }

    assert(wallAfter10Draws.liveTiles.length == wall.liveTiles.length - 10)
  }

  test("revealDora should reveal a dora indicator from the dead wall") {
    val wall = Wall.initialize(identity)
    val initialDeadWall = wall.deadWall
    val initialDoraCount = wall.doraIndicators.length

    val result = wall.revealDora()
    assert(result.isRight)

    val (revealedDora, newWall) = result.getOrElse(fail("Dora reveal failed"))

    assert(initialDeadWall.head == revealedDora)
    assert(newWall.deadWall.length == initialDeadWall.length - 1)
    assert(newWall.doraIndicators.length == initialDoraCount + 1)
    assert(newWall.doraIndicators.last == revealedDora)
  }

  test("Wall should be initialized with exactly 136 tiles") {
    val wall = Wall.initialize(identity)
    val totalTiles = wall.liveTiles.length + wall.deadWall.length + wall.doraIndicators.length
    assert(totalTiles == 136)
  }

  test("Wall should contain the correct distribution of tile types") {
    val wall = Wall.initialize(identity)
    val allTiles = wall.liveTiles ++ wall.deadWall ++ wall.doraIndicators

    val suitTiles = allTiles.count(_.tileType.isInstanceOf[TileType.Suit])
    val windTiles = allTiles.count(_.tileType.isInstanceOf[TileType.Wind])
    val dragonTiles = allTiles.count(_.tileType.isInstanceOf[TileType.Dragon])

    assert(suitTiles == 108) // 3 suits × 9 numbers × 4 of each
    assert(windTiles == 16) // 4 winds × 4 of each
    assert(dragonTiles == 12) // 3 dragons × 4 of each
  }

  test("Custom shuffle function should be applied during initialization") {
    // Create a shuffle function that reverses the tiles
    val reverseShuffler = (tiles: Vector[Tile]) => tiles.reverse

    val standardWall = Wall.initialize(identity)
    val reversedWall = Wall.initialize(reverseShuffler)

    // The walls should have different live tile orderings
    assert(standardWall.liveTiles != reversedWall.liveTiles)
  }
}

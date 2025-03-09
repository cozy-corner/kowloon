package kowloon.core.domain.model.tile

import org.scalatest.funsuite.AnyFunSuiteLike

class TileTest extends AnyFunSuiteLike {

  test("createSuit should create valid suit tiles") {
    val character5 = Tile.createSuit(TileType.Suit.Character(5), 5)
    val bamboo3 = Tile.createSuit(TileType.Suit.Bamboo(3), 3)
    val dot9 = Tile.createSuit(TileType.Suit.Dot(9), 9)

    assert(character5.isRight)
    assert(bamboo3.isRight)
    assert(dot9.isRight)

    assert(character5.map(_.tileType).contains(TileType.Suit.Character(5)))
    assert(bamboo3.map(_.tileType).contains(TileType.Suit.Bamboo(3)))
    assert(dot9.map(_.tileType).contains(TileType.Suit.Dot(9)))
  }

  test("createSuit should reject invalid numbers") {
    val invalidLow = Tile.createSuit(TileType.Suit.Character(1), 0)
    val invalidHigh = Tile.createSuit(TileType.Suit.Bamboo(9), 10)

    assert(invalidLow.isLeft)
    assert(invalidHigh.isLeft)
  }

  test("createWind should create valid wind tiles") {
    val east = Tile.createWind(TileType.Wind.East)
    val south = Tile.createWind(TileType.Wind.South)
    val west = Tile.createWind(TileType.Wind.West)
    val north = Tile.createWind(TileType.Wind.North)

    assert(east.tileType == TileType.Wind.East)
    assert(south.tileType == TileType.Wind.South)
    assert(west.tileType == TileType.Wind.West)
    assert(north.tileType == TileType.Wind.North)

    assert(east.number.isEmpty)
    assert(south.number.isEmpty)
    assert(west.number.isEmpty)
    assert(north.number.isEmpty)
  }

  test("createDragon should create valid dragon tiles") {
    val white = Tile.createDragon(TileType.Dragon.White)
    val green = Tile.createDragon(TileType.Dragon.Green)
    val red = Tile.createDragon(TileType.Dragon.Red)

    assert(white.tileType == TileType.Dragon.White)
    assert(green.tileType == TileType.Dragon.Green)
    assert(red.tileType == TileType.Dragon.Red)

    assert(white.number.isEmpty)
    assert(green.number.isEmpty)
    assert(red.number.isEmpty)
  }

  test("isTerminal should identify terminal tiles correctly") {
    val character1 = Tile.createSuit(TileType.Suit.Character(1), 1).toOption.get
    val character5 = Tile.createSuit(TileType.Suit.Character(5), 5).toOption.get
    val character9 = Tile.createSuit(TileType.Suit.Character(9), 9).toOption.get
    val bamboo1 = Tile.createSuit(TileType.Suit.Bamboo(1), 1).toOption.get
    val bamboo9 = Tile.createSuit(TileType.Suit.Bamboo(9), 9).toOption.get
    val dot1 = Tile.createSuit(TileType.Suit.Dot(1), 1).toOption.get
    val dot9 = Tile.createSuit(TileType.Suit.Dot(9), 9).toOption.get
    val east = Tile.createWind(TileType.Wind.East)
    val white = Tile.createDragon(TileType.Dragon.White)

    assert(Tile.isTerminal(character1))
    assert(!Tile.isTerminal(character5))
    assert(Tile.isTerminal(character9))
    assert(Tile.isTerminal(bamboo1))
    assert(Tile.isTerminal(bamboo9))
    assert(Tile.isTerminal(dot1))
    assert(Tile.isTerminal(dot9))
    assert(!Tile.isTerminal(east))
    assert(!Tile.isTerminal(white))
  }
}

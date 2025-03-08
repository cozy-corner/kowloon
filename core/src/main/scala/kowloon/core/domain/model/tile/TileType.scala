package kowloon.core.domain.model.tile 
sealed trait TileType
object TileType {
  // 数牌（1-9のみ有効）
  sealed trait Suit extends TileType {
    def number: Int
    require(number >= 1 && number <= 9, "数牌は1〜9でなければなりません")
  }
  object Suit {
    case class Character(number: Int) extends Suit

    case class Dot(number: Int) extends Suit

    case class Bamboo(number: Int) extends Suit
  }

  // 風牌（東南西北）
  sealed trait Wind extends TileType
  object Wind {
    case object East extends Wind
    case object South extends Wind
    case object West extends Wind
    case object North extends Wind
  }

  // 三元牌（白發中）
  sealed trait Dragon extends TileType
  object Dragon {
    case object White extends Dragon
    case object Green extends Dragon
    case object Red extends Dragon
  }
}

package kowloon.core.domain.model.game

import kowloon.core.domain.model.tile.TileType.Wind

// 場と局数を分離
case class Round(
    fieldWind: Wind,
    number: Int
) {
  require(number >= 1 && number <= 4, "局数は1〜4でなければなりません")
}

object Round {
  object Round {
    def apply(wind: Wind, number: Int): Round =
      new Round(wind, number)
  }
}

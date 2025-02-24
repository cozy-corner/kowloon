package kowloon.core.model.yaku

import kowloon.core.model.player.PlayerId
import kowloon.core.model.state.GameState

case class Yaku(
                 name: String,
                 hanValue: Int,
                 conditions: GameState => Boolean
               )

case class ScoreCalculation(
                             basePoints: Int,
                             multipliers: Map[PlayerId, Double],
                             limitHand: Option[LimitHandType]
                           )

enum LimitHandType:
    case Mangan, Haneman, Baiman, Sanbaiman, Yakuman
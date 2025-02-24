package kowloon.core.model.state

import kowloon.core.model.action.PlayerAction
import kowloon.core.model.phase.{GamePhase, PlayerState}
import kowloon.core.model.player.PlayerId
import kowloon.core.model.tile.Tile

import java.time.Instant

case class GameState(
                      phase: GamePhase,
                      wall: List[Tile],
                      deadWall: List[Tile],
                      doraIndicators: List[Tile],
                      players: Map[PlayerId, PlayerState],
                      currentTurn: PlayerId,
                      actionHistory: List[ActionLog]
                    )

case class ActionLog(
                      playerId: PlayerId,
                      action: PlayerAction,
                      timestamp: Instant
                    )
package kowloon.core.domain.model.game

import kowloon.core.domain.model.player.{Player, PlayerId}

final case class Game(
                      id: GameId,
                      wall: Wall,
                      discards: DiscardPile,
                      players: Map[PlayerId, Player],
                      phase: GamePhase
                    )

case class GameId(value: String) extends AnyVal

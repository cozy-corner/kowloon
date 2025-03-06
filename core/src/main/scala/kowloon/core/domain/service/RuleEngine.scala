package kowloon.core.domain.service

import kowloon.core.domain.model.game.Game
import kowloon.core.domain.model.player.PlayerId
import kowloon.core.domain.model.tile.Tile

trait RuleEngine {
  def validateDiscard(game: Game, playerId: PlayerId, tile: Tile): Boolean = ???
}

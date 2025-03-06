package kowloon.core.domain.repository

import kowloon.core.domain.model.game.{Game, GameId}

trait GameRepository[F[_]] {
  def save(game: Game): F[Unit]
  def find(id: GameId): F[Option[Game]]
}

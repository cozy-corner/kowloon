package kowloon.core.model.action

import kowloon.core.model.tile.Tile

enum PlayerAction:
    case Discard(tile: Tile)
    case Tsumo
    case CallPon(calledTile: Tile)
    case CallChi(tile1: Tile, tile2: Tile)
    case DeclareRiichi
    case Kan(kanType: KanType)

enum KanType:
    case Closed, Open, Added
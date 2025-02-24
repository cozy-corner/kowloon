package kowloon.core.model.tile

enum Tile:
    case Suit(suitType: SuitType, number: 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9)
    case Wind(windType: WindType)
    case Dragon(dragonType: DragonType)

enum SuitType:
    case Character, Bamboo, Dot

enum WindType:
    case East, South, West, North

enum DragonType:
    case Red, Green, White
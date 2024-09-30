package gamedecisions

import gamedecisions.Outcome.*

data object Paper : AbsGameDecision("Paper", "[P|p](?:aper)?") {
    override fun beats(other: GameDecision): Outcome = when (other) {
        is Rock -> WIN
        is Paper -> DRAW
        else -> LOSE
    }
}
package gamedecisions

import gamedecisions.Outcome.*

data object Rock : AbsGameDecision("Rock", "[R|r](?:ock)?") {
    override fun beats(other: GameDecision): Outcome = when (other) {
        is Scissors -> WIN
        is Rock -> DRAW
        else -> LOSE
    }
}
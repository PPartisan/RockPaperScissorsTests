package gamedecisions

import gamedecisions.Outcome.*

data object Scissors : AbsGameDecision("Scissors", "[S|s](?:cissors)?") {
    override fun beats(other: GameDecision): Outcome = when (other) {
        is Paper -> WIN
        is Scissors -> DRAW
        else -> LOSE
    }
}
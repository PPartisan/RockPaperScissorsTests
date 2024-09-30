package gamedecisions

sealed interface GameDecision {
    fun beats(other: GameDecision) : Outcome
    fun matches(name: String) : Boolean
}
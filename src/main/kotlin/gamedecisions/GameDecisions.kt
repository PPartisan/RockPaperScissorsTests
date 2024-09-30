package gamedecisions

class GameDecisions(
    private val decisions: List<GameDecision>,
    private val rnd: (List<GameDecision>) -> GameDecision = { it.random() }
) {

    fun forInput(input: String) : GameDecision {
        val decision = decisions.find { it.matches(input) }
        if(decision == null)
            throw IllegalStateException("Unrecognised input: $input")
        return decision
    }

    fun any() : GameDecision = rnd(decisions)

}
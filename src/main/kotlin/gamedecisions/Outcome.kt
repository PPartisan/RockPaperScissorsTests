package gamedecisions

enum class Outcome(private val displayable: String){
    WIN("Win :)"), LOSE("Lose :("), DRAW("Draw :/");
    override fun toString(): String = displayable
}
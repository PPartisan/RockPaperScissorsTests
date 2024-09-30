import gamedecisions.GameDecisions

class Game(private val decisions: GameDecisions) {

    fun start() {
        println("Enter choice:")
        val userChoice = decisions.forInput(readln())
        println("Player choice: $userChoice")

        val compChoice = decisions.any()
        println("Computer choice: $compChoice")

        println("You ${userChoice.beats(compChoice)}")
    }

}
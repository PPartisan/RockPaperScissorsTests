fun main() {
    val game = Game()
    game()
}

open class Game(
    private val rnd: (List<String>) -> String = { it.random() }
) {
    open operator fun invoke() {
        val choices = listOf("rock", "paper", "scissors")
        val compChoice = rnd(choices)
        println("Enter choice:")
        val userIn = readln()
        if(userIn !in choices) {
            println("Invalid input: $userIn")
            return
        }
        println("Computer choice: $compChoice")
        if(compChoice == userIn) {
            println("draw")
            return
        }
        when("$compChoice $userIn") {
            "rock paper" -> println("win")
            "paper scissors" -> println("win")
            "scissors rock" -> println("win")
            else -> println("lose")
        }
    }
}

class RepeatGames(
    private val generator: () -> Game = { Game() }
) {
    fun start() {
        do {
            generator()()
        } while(readln() != "n")
    }
}
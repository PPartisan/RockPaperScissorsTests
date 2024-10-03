fun main() = RepeatGames().start()

class Game(
    private val rnd: (List<String>) -> String = { it.random() },
    private val input: UserInput = UserInput(),
    private val output: UserOutput = UserOutput()
) {
    operator fun invoke() {
        val choices = listOf("rock", "paper", "scissors")
        val compChoice = rnd(choices)
        output.display("Enter choice:")
        val userIn = input.line()
        if(userIn !in choices) {
            output.display("Invalid input: $userIn")
            return
        }

        output.display("Computer choice: $compChoice")
        if(compChoice == userIn) {
            output.display("draw")
            return
        }
        when("$compChoice $userIn") {
            "rock paper" -> output.display("win")
            "paper scissors" -> output.display("win")
            "scissors rock" -> output.display("win")
            else -> output.display("lose")
        }
    }
}

class RepeatGames(
    private val output: UserOutput = UserOutput(),
    private val input: UserInput = UserInput(),
    private val generator: () -> Game = { Game() }
) {
    fun start() {
        do {
            generator()()
            output.display("Play another game (Enter 'n' or 'no' to exit)?")
        } while(input.line().orEmpty().lowercase() !in arrayOf("n", "no"))
    }
}

class UserInput {
    fun line() : String? = readlnOrNull()
}

class UserOutput {
    fun display(line: String) = println(line)
}

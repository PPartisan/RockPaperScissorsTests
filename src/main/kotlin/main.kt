fun main() {
    val game = Game()
    game()
}

class Game(
    private val rnd: (List<String>) -> String = { it.random() }
) {
   operator fun invoke() {
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
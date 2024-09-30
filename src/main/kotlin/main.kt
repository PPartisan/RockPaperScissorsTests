import gamedecisions.GameDecisions
import gamedecisions.Paper
import gamedecisions.Rock
import gamedecisions.Scissors

fun main() {
    listOf(Rock, Paper, Scissors)
        .let(::GameDecisions)
        .let(::Game)
        .also(Game::start)
}
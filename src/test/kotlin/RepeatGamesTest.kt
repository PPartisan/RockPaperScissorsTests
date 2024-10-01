import io.kotest.matchers.ints.shouldBeExactly
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class RepeatGamesTest {

    private lateinit var input: UserInput
    private lateinit var output: UserOutput
    private lateinit var game: Game
    private lateinit var repeat: RepeatGames

    @BeforeEach
    fun setUp() {
        input = mockk()
        every { input.line() } returns ""

        output = mockk()
        every { output.display(any()) } just runs

        game = mockk<Game>()
        every { game() } just runs

        repeat = RepeatGames(output, input) { game }
    }

    @Test
    fun `given game finished, when user enters n on keyboard, then run one game`() {
        "n".enterUserInput()
        repeat.start()
        verify(exactly = 1) { game() }
    }

    @Test
    fun `given game finished, when user enters no on keyboard, then run one game`() {
        "no".enterUserInput()
        repeat.start()
        verify(exactly = 1) { game() }
    }

    @Test
    fun `given game finished, when user requests another game, and then enters n on keyboard, then run two games `() {
        listOf("y", "n").enterUserInput()
        repeat.start()
        verify(exactly = 2) { game() }
    }

    @Test
    fun `given game finished, when user requests another game with no input, and then enters n on keyboard, then run two games `() {
        listOf("", "n").enterUserInput()
        repeat.start()
        verify(exactly = 2) { game() }
    }

    private fun String.enterUserInput() =
        listOf(this).enterUserInput()
    private fun List<String>.enterUserInput() =
        every { input.line() } returnsMany this

}
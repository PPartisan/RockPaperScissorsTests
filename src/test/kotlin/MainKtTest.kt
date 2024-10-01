import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldEndWith
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainKtTest {

    private lateinit var input: UserInput
    private lateinit var output: UserOutput

    @BeforeEach
    fun setUp() {
        input = mockk()
        every { input.line() } returns ""

        output = mockk()
        every { output.display(any()) } just runs
    }

    @Test
    fun `when user enters unrecognised input, then exit with invalid input warning`() = with("Invalid input") {
        enterUserInput()
        runGame()
        verifyOutput {
            it shouldEndWith "Invalid input: $this"
        }
    }


    @Test
    fun `when computer player randomly selects rock, then print computer player choice`() {
        "rock".enterUserInput()
        val computer = "scissors"
        runGameWithComputerChoice(computer)
        verifyOutput {
            it shouldContain "Computer choice: $computer"
        }
    }

    @Test
    fun `when computer selects rock, and user selects paper, then user wins`() {
        "paper".enterUserInput()
        runGameWithComputerChoice("rock")
        assertUserWin()
    }

    @Test
    fun `when computer selects paper, and user selects scissors, then user wins`() {
        "scissors".enterUserInput()
        runGameWithComputerChoice("paper")
        assertUserWin()
    }

    @Test
    fun `when computer selects scissors, and user selects rock, then user wins`() {
        "rock".enterUserInput()
        runGameWithComputerChoice("scissors")
        assertUserWin()
    }

    @Test
    fun `when computer selects rock, and user selects scissors, then user loses`() {
        "scissors".enterUserInput()
        runGame { "rock" }
        assertUserLoss()
    }

    @Test
    fun `when computer selects paper, and user selects rock, then user loses`() {
        "rock".enterUserInput()
        runGameWithComputerChoice( "paper")
        assertUserLoss()
    }

    @Test
    fun `when computer selects scissors, and user selects paper, then user loses`() {
        "paper".enterUserInput()
        runGameWithComputerChoice("scissors")
        assertUserLoss()
    }

    @Test
    fun `when computer selects scissors, and user selects scissors, then user draws`() = with("scissors") {
        enterUserInput()
        runGameWithComputerChoice(this)
        assertUserDraw()
    }

    @Test
    fun `when computer selects rock, and user selects rock, then user draws`() = with("rock") {
        enterUserInput()
        runGameWithComputerChoice(this)
        assertUserDraw()
    }


    @Test
    fun `when computer selects paper, and user selects paper, then user draws`() = with("paper") {
        enterUserInput()
        runGameWithComputerChoice(this)
        assertUserDraw()
    }

    private fun runGameWithComputerChoice(choice: String) =
        runGame { choice }

    private fun runGame(rnd: (List<String>) -> String = { it.random() }) {
        Game(rnd, input, output)()
    }

    private fun String.enterUserInput() =
        every { input.line() } returns this

    private fun verifyOutput(block: (String) -> Unit) {
        verify { output.display(withArg { block(it) }) }
    }

    private fun assertUserWin() =
        verifyOutput { it shouldEndWith "win" }

    private fun assertUserLoss() =
        verifyOutput { it shouldEndWith "lose" }

    private fun assertUserDraw() =
        verifyOutput { it shouldEndWith "draw" }
}

import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldEndWith
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MainKtTest {

    private val oIn = System.`in`
    private val oOut = System.out

    private lateinit var console: ByteArrayOutputStream

    @BeforeEach
    fun setUp() {
        console = ByteArrayOutputStream()
        System.setOut(PrintStream(console))
    }

    @AfterEach
    fun tearDown() {
        System.setIn(oIn)
        System.setOut(oOut)
    }

    @Test
    fun `when user enters unrecognised input, then exit with invalid input warning`() {
        val user = "Neither a rock nor paper nor scissors"
        user.enterAsUserInput()
        runGame()
        console.asString() shouldEndWith "Invalid input: $user"

    }

    @Test
    fun `when computer player randomly selects rock, then print computer player choice`() {
        "rock".enterAsUserInput()
        val computer = "scissors"
        runGameWithComputerChoice(computer)
        console.asString() shouldContain "Computer choice: $computer"
    }

    @Test
    fun `when computer selects rock, and user selects paper, then user wins`() {
        "paper".enterAsUserInput()
        runGameWithComputerChoice("rock")
        console.asString() shouldEndWith "win"
    }

    @Test
    fun `when computer selects paper, and user selects scissors, then user wins`() {
        "scissors".enterAsUserInput()
        runGameWithComputerChoice("paper")
        console.asString() shouldEndWith "win"
    }

    @Test
    fun `when computer selects scissors, and user selects rock, then user wins`() {
        "rock".enterAsUserInput()
        runGameWithComputerChoice("scissors")
        console.asString() shouldEndWith "win"
    }

    @Test
    fun `when computer selects rock, and user selects scissors, then user loses`() {
        "scissors".enterAsUserInput()
        runGame { "rock" }
        console.asString() shouldEndWith "lose"
    }

    @Test
    fun `when computer selects paper, and user selects rock, then user loses`() {
        "rock".enterAsUserInput()
        runGameWithComputerChoice( "paper")
        console.asString() shouldEndWith "lose"
    }

    @Test
    fun `when computer selects scissors, and user selects paper, then user loses`() {
        "paper".enterAsUserInput()
        runGameWithComputerChoice("scissors")
        console.asString() shouldEndWith "lose"
    }

    private fun runGameWithComputerChoice(choice: String) =
        runGame { choice }

    private fun runGame(rnd: (List<String>) -> String = { it.random() }) {
        Game(rnd)()
    }

    private fun String.enterAsUserInput() =
        System.setIn(ByteArrayInputStream(toByteArray()))
    private fun ByteArrayOutputStream.asString() =
        toString().trim()
}
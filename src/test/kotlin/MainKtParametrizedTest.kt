import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertContains

class MainKtParametrizedTest {

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

    @ParameterizedTest
    @MethodSource("testRockPaperScissorsParameters")
    fun testRockPaperScissors(userInput: String, computerInput: String, expected: String, msg: String = "") {
        userInput.enterAsUserInput()
        runGameWithComputerChoice(computerInput)
        assertContains(console.asString(), expected, message = msg)
    }

    companion object {
        @JvmStatic
        fun testRockPaperScissorsParameters(): List<Array<Any>> {
            return listOf(
                arrayOf(
                    "Neither a rock nor paper nor scissors",
                    "rock",
                    "Invalid input: Neither a rock nor paper nor scissors",
                    "when user enters unrecognised input, then exit with invalid input warning",
                ),
                arrayOf(
                    "rock",
                    "scissors",
                    "Computer choice: scissors",
                    "when computer player randomly selects rock, then print computer player choice",
                ),
                arrayOf(
                    "paper",
                    "scissors",
                    "win",
                    "when computer selects rock, and user selects paper, then user wins",
                ),
                arrayOf(
                    "paper",
                    "rock",
                    "win",
                    "when computer selects rock, and user selects paper, then user wins",
                ),
                arrayOf(
                    "scissors",
                    "paper",
                    "win",
                    "when computer selects paper, and user selects scissors, then user wins",
                ),
                arrayOf(
                    "rock",
                    "scissors",
                    "win",
                    "when computer selects scissors, and user selects rock, then user wins",
                ),
                arrayOf(
                    "scissors",
                    "rock",
                    "lose",
                    "when computer selects rock, and user selects scissors, then user loses",
                ),
                arrayOf(
                    "rock",
                    "paper",
                    "lose",
                    "when computer selects paper, and user selects rock, then user loses",
                ),
                arrayOf(
                    "paper",
                    "scissors",
                    "lose",
                    "when computer selects scissors, and user selects paper, then user loses"
                )
            )
        }
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
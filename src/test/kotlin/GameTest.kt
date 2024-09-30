import gamedecisions.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldEndWith
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GameTest {

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
    fun `when user enters unrecognised input, then exit with exception`() {
        val exception = shouldThrow<IllegalStateException> {
            val user = "Neither a rock nor paper nor scissors"
            user.enterAsUserInput()
            runGame()
        }
        exception.message shouldBe "Unrecognised input: Neither a rock nor paper nor scissors"
    }

    @Test
    fun `when computer player randomly selects scissors, then print computer player choice`() {
        Rock.enterAsUserInput()
        val computer = Scissors
        runGameWithComputerChoice(computer)
        console.asString() shouldContain "Computer choice: $computer"
    }

    @Test
    fun `when computer selects rock, and user selects paper, then user wins`() {
        Paper.enterAsUserInput()
        runGameWithComputerChoice(Rock)
        console.asString() shouldEndWith ":)"
    }

    @Test
    fun `when computer selects paper, and user selects scissors, then user wins`() {
        Scissors.enterAsUserInput()
        runGameWithComputerChoice(Paper)
        console.asString() shouldEndWith ":)"
    }

    @Test
    fun `when computer selects scissors, and user selects rock, then user wins`() {
        Rock.enterAsUserInput()
        runGameWithComputerChoice(Scissors)
        console.asString() shouldEndWith ":)"
    }

    @Test
    fun `when computer selects rock, and user selects scissors, then user loses`() {
        Scissors.enterAsUserInput()
        runGameWithComputerChoice(Rock)
        console.asString() shouldEndWith ":("
    }

    @Test
    fun `when computer selects paper, and user selects rock, then user loses`() {
        Rock.enterAsUserInput()
        runGameWithComputerChoice(Paper)
        console.asString() shouldEndWith ":("
    }

    @Test
    fun `when computer selects scissors, and user selects paper, then user loses`() {
        Paper.enterAsUserInput()
        runGameWithComputerChoice(Scissors)
        console.asString() shouldEndWith ":("
    }

    @Test
    fun `when computer selects paper, and user selects paper, then user draws`() {
        Paper.enterAsUserInput()
        runGameWithComputerChoice(Paper)
        console.asString() shouldEndWith ":/"
    }

    @Test
    fun `when computer selects rock, and user selects rock, then user draws`() {
        Rock.enterAsUserInput()
        runGameWithComputerChoice(Rock)
        console.asString() shouldEndWith ":/"
    }

    @Test
    fun `when computer selects scissors, and user selects scissors, then user draws`() {
        Scissors.enterAsUserInput()
        runGameWithComputerChoice(Scissors)
        console.asString() shouldEndWith ":/"
    }

    @Test
    fun `when user selects valid choice, then print player choice`() {
        Rock.enterAsUserInput()
        runGameWithComputerChoice(Rock)
        console.asString() shouldContain "Player choice: $Rock"
    }

    private fun runGameWithComputerChoice(choice: GameDecision) =
        runGame { choice }

    private fun runGame(rnd: (List<GameDecision>) -> GameDecision = { it.random() }) {
        val decisions = GameDecisions(listOf(Rock, Paper, Scissors), rnd)
        Game(decisions).start()
    }

    private fun GameDecision.enterAsUserInput() =
        toString().enterAsUserInput()
    private fun String.enterAsUserInput() =
        System.setIn(ByteArrayInputStream(toByteArray()))
    private fun ByteArrayOutputStream.asString() =
        toString().trim()
}
import io.kotest.matchers.ints.shouldBeExactly
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class RepeatGamesTest {

    private val oIn = System.`in`

    private var counter : Int = 0

    private lateinit var repeat: RepeatGames

    @BeforeEach
    fun setUp() {
        val generator : () -> Game = { object: Game() {
            override operator fun invoke() { counter++ }
        } }
        repeat = RepeatGames(generator)
    }

    @AfterEach
    fun tearDown() {
        counter = 0
        System.setIn(oIn)
    }

    @Test
    fun `given game finished, when user enters n on keyboard, then run one game`() {
        "n".enterAsUserInput()
        repeat.start()
        counter shouldBeExactly 1
    }

    @Test
    fun `given game finished, when user requests another game, and then enters n on keyboard, then run two games `() {
        "y\nn".enterAsUserInput()
        repeat.start()
        counter shouldBeExactly 2
    }

    companion object {
        private fun String.enterAsUserInput() =
            System.setIn(ByteArrayInputStream(toByteArray()))
    }

}
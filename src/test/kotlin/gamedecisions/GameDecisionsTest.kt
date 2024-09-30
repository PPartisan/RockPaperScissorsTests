package gamedecisions

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameDecisionsTest {

    private lateinit var decisions: GameDecisions

    @BeforeEach
    fun setUp() {
        decisions = GameDecisions(opts)
    }

    @Test
    fun `when unrecognised input, then throw illegal state exception`() {
        val input = "This input will match nothing"
        shouldThrow<IllegalStateException> { decisions.forInput(input) }.message shouldBe "Unrecognised input: $input"
    }

    @Test
    fun `when recognised input, then return decision for input`() {
        opts.forEach {
            with(it) {
                decisions.forInput(toString()) shouldBe this
            }
        }
    }

    @Test
    fun `when random requesting decision, then decision is valid`() {
        decisions.any() shouldBeIn opts
    }

    companion object {
        private val opts = listOf(Rock, Paper, Scissors)
    }

}
package gamedecisions

import gamedecisions.Outcome.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScissorsTest {

    @Test
    fun `when other is paper, then scissors beats paper`() {
        Scissors.beats(Paper) shouldBe WIN
    }

    @Test
    fun `when other is scissors, then scissors draws with scissors`() {
        Scissors.beats(Scissors) shouldBe DRAW
    }

    @Test
    fun `when other is rock, scissors loses to rock`() {
        Scissors.beats(Rock) shouldBe LOSE
    }

    @Test
    fun `when input is recognised short form or long form, then match is true`() {
        listOf("s", "S", "Scissors", "scissors").forEach { Scissors.matches(it) shouldBe true }
    }

    @Test
    fun `when input is unrecognised short form or long form, then match is false`() {
        listOf("p", "P", "Paper", "paper").forEach { Scissors.matches(it) shouldBe false }
    }
}
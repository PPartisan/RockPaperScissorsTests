package gamedecisions

import gamedecisions.Outcome.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PaperTest {

    @Test
    fun `when other is rock, then paper beats rock`() {
        Paper.beats(Rock) shouldBe WIN
    }

    @Test
    fun `when other is paper, then paper draws with paper`() {
        Paper.beats(Paper) shouldBe DRAW
    }

    @Test
    fun `when other is scissors, paper loses to scissors`() {
        Paper.beats(Scissors) shouldBe LOSE
    }

    @Test
    fun `when input is recognised short form or long form, then match is true`() {
        listOf("p", "P", "Paper", "paper").forEach { Paper.matches(it) shouldBe true }
    }

    @Test
    fun `when input is unrecognised short form or long form, then match is false`() {
        listOf("r", "R", "Rock", "rock").forEach { Paper.matches(it) shouldBe false }
    }
}
package gamedecisions

import gamedecisions.Outcome.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RockTest {

    @Test
    fun `when other is scissors, then rock beats scissors`() {
        Rock.beats(Scissors) shouldBe WIN
    }

    @Test
    fun `when other is rock, then rock draws with rock`() {
        Rock.beats(Rock) shouldBe DRAW
    }

    @Test
    fun `when other is paper, rock loses to paper`() {
        Rock.beats(Paper) shouldBe LOSE
    }

    @Test
    fun `when input is recognised short form or long form, then match is true`() {
        listOf("r", "R", "Rock", "rock").forEach { Rock.matches(it) shouldBe true }
    }

    @Test
    fun `when input is unrecognised short form or long form, then match is false`() {
        listOf("p", "P", "Paper", "paper").forEach { Rock.matches(it) shouldBe false }
    }
}
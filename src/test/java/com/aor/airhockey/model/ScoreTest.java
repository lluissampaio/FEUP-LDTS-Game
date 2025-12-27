package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreTest {
    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(10, 20);
    }

    @Test
    void initialization() {
        Assertions.assertEquals(10, score.getPosition().getX());
        Assertions.assertEquals(20, score.getPosition().getY());

        Assertions.assertEquals(0, score.getPlayer1Points());
        Assertions.assertEquals(0, score.getPlayer2Points());
    }

    @Test
    void incrementPlayer1() {
        score.incrementPlayer1Points();
        Assertions.assertEquals(1, score.getPlayer1Points());
        Assertions.assertEquals(0, score.getPlayer2Points());

        score.incrementPlayer1Points();
        Assertions.assertEquals(2, score.getPlayer1Points());
    }

    @Test
    void incrementPlayer2() {
        score.incrementPlayer2Points();
        Assertions.assertEquals(1, score.getPlayer2Points());
        Assertions.assertEquals(0, score.getPlayer1Points());
    }
}

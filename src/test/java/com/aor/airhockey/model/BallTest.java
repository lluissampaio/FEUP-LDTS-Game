package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Ball;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BallTest {
    private Ball ball;

    @BeforeEach
    void setUp() {
        ball = new Ball(10, 10, 5, 2, 3);
    }

    @Test
    void constructorAndGetters() {
        Assertions.assertEquals(10, ball.getPosition().getX());
        Assertions.assertEquals(10, ball.getPosition().getY());

        Assertions.assertEquals(5, ball.getRadius());

        Assertions.assertEquals(2, ball.getVelocity().getX());
        Assertions.assertEquals(3, ball.getVelocity().getY());
    }

    @Test
    void setRadius() {
        ball.setRadius(10);
        Assertions.assertEquals(10, ball.getRadius());
    }

    @Test
    void scoringLogic() {
        Assertions.assertEquals(0, ball.getScoringPlayer());//estado inicial

        ball.player1Scored();
        Assertions.assertEquals(1, ball.getScoringPlayer());

        ball.resetScoringPlayer();
        Assertions.assertEquals(0, ball.getScoringPlayer());

        ball.player2Scored();
        Assertions.assertEquals(2, ball.getScoringPlayer());
    }

    @Test
    void secondaryConstructor() {
        Ball defaultBall = new Ball(20, 20, 3);

        Assertions.assertEquals(20, defaultBall.getPosition().getX());
        Assertions.assertEquals(20, defaultBall.getPosition().getY());
        Assertions.assertEquals(3, defaultBall.getRadius());

        Assertions.assertNotNull(defaultBall.getVelocity());//not  null porque o valor default pode mudar
    }
}
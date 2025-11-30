package com.aor.airhockey.controller;

import com.aor.airhockey.controller.game.BallController;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Wall;
import com.aor.airhockey.model.game.field.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class BallControllerTest {
    private BallController controller;
    private Ball ball;
    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field(10, 10);

        ball = new Ball(5, 5, 2, 1, 1);
        field.setBall(ball);

        field.setWalls(Arrays.asList());

        controller = new BallController(field);
    }

    @Test
    void moveBallEmpty() {
        controller.moveBall();
        Assertions.assertEquals(new Position(6, 6), ball.getPosition());
    }

    @Test
    void moveBallCollisionHorWall() {
        field.setWalls(Arrays.asList(new Wall(5,6)));
        controller.moveBall();
        Assertions.assertEquals(new Position(6, 4), ball.getPosition());
    }

    @Test
    void moveBallCollisionVertWall() {
        field.setWalls(Arrays.asList(new Wall(6,5)));
        controller.moveBall();
        Assertions.assertEquals(new Position(4, 6), ball.getPosition());
    }
}

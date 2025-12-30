package com.aor.airhockey.controller;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.collision.CircleCollisionDetector;
import com.aor.airhockey.controller.collision.CollisionAxis;
import com.aor.airhockey.controller.collision.CollisionInfo;
import com.aor.airhockey.controller.collision.FieldCollisionDetector;
import com.aor.airhockey.controller.game.BallController;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;

class BallControllerTest {
    private BallController controller;
    private Ball ball;
    private Disk disk1;
    private Disk disk2;
    private Field field;
    private Game game;
    private FieldCollisionDetector wallCollisionDetector;
    private CircleCollisionDetector diskCollisionDetector;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(Field.class);
        game = Mockito.mock(Game.class);

        ball = new Ball(1, 1, 1, 0, 0);
        disk1 = new Disk(3, 3, 2, 1);
        disk2 = new Disk(5, 5, 2, 2);

        Mockito.when(field.getBall()).thenReturn(ball);
        Mockito.when(field.getDisk1()).thenReturn(disk1);
        Mockito.when(field.getDisk2()).thenReturn(disk2);

        wallCollisionDetector = Mockito.mock(FieldCollisionDetector.class);
        diskCollisionDetector = Mockito.mock(CircleCollisionDetector.class);

        Mockito.when(wallCollisionDetector.collisionAxis(any(), anyDouble())).thenReturn(CollisionAxis.NONE);
        Mockito.when(diskCollisionDetector.collisionInfo(any(), anyDouble(), any(), anyDouble())).thenReturn(null);

        controller = new BallController(field, wallCollisionDetector, diskCollisionDetector);
    }

    @Test
    void noCollision() {
        ball.setVelocity(new Velocity(2, 1));

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertEquals(3.0, ball.getPosition().getX(), 0.01);
        Assertions.assertEquals(2.0, ball.getPosition().getY(), 0.01);
    }

    @Test
    void hitHorizontalWall() {
        ball.setVelocity(new Velocity(2, 1));

        Mockito.when(wallCollisionDetector.collisionAxis(any(), anyDouble())).thenReturn(CollisionAxis.HORIZONTAL);

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertTrue(ball.getVelocity().getY() < 0);
    }

    @Test
    void hitVerticalWall() {
        ball.setVelocity(new Velocity(2, 1));

        Mockito.when(wallCollisionDetector.collisionAxis(any(), anyDouble())).thenReturn(CollisionAxis.VERTICAL);

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertTrue(ball.getVelocity().getX() < 0);
    }

    @Test
    void hitDisk(){
        ball.setVelocity(new Velocity(2, 2));

        CollisionInfo info = new CollisionInfo(3, 5, 0.5); // these values dont really matter they only serve as placeholders

        Mockito.when(diskCollisionDetector.collisionInfo(any(), anyDouble(), any(), anyDouble())).thenReturn(info);

        controller.step(game, Collections.emptySet(), 100);

        Mockito.verify(diskCollisionDetector, Mockito.atLeastOnce()).collisionInfo(any(), anyDouble(), any(), anyDouble()); //verify if collision info was called
    }

}

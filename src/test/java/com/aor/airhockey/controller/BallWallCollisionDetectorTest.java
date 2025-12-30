package com.aor.airhockey.controller;

import com.aor.airhockey.controller.collision.BallWallCollisionDetector;
import com.aor.airhockey.controller.collision.CollisionAxis;
import com.aor.airhockey.controller.collision.DiskObjectCollisionDetector;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.field.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class BallWallCollisionDetectorTest {
    private BallWallCollisionDetector detector;
    private Field field;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(Field.class);
        detector = new BallWallCollisionDetector(field);

    }

    @Test
    void noWalls() {
        Position position = new Position(0, 0);
        double radius = 1.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.NONE, axis);
    }

    @Test
    void noCollision(){
        Mockito.when(field.isWall(new Position(0,2))).thenReturn(true);

        Position position = new Position(3, 7);
        double radius = 2.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.NONE, axis);
    }

    @Test
    void horizontalCollision(){
        Mockito.when(field.isWall(new Position(2, 1))).thenReturn(true);

        Position position = new Position(2, 3);
        double radius = 2.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.HORIZONTAL, axis);
    }

    @Test
    void verticalCollision(){
        Mockito.when(field.isWall(new Position(1, 2))).thenReturn(true);

        Position position = new Position(3, 2);
        double radius = 2.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.VERTICAL, axis);
    }

    @Test
    void bothCollisions(){
        Mockito.when(field.isWall(new Position(3, 0))).thenReturn(true);
        Mockito.when(field.isWall(new Position(1, 2))).thenReturn(true);

        Position position = new Position(3, 2);
        double radius = 2.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.BOTH , axis);
    }
}

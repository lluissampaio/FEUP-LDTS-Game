package com.aor.airhockey.controller;

import com.aor.airhockey.controller.collision.CollisionAxis;
import com.aor.airhockey.controller.collision.DiskObjectCollisionDetector;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.field.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class DiskObjectCollisionDetectorTest {
    private DiskObjectCollisionDetector detector;
    private Field field;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(Field.class);
        detector = new  DiskObjectCollisionDetector(field);

        Mockito.when(field.isEmpty(any(Position.class))).thenReturn(true);
    }

    @Test
    void emptyField() {
        Position position = new Position(0, 0);
        double radius = 1.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.NONE, axis);
    }

    @Test
    void noCollision(){
        Mockito.when(field.isEmpty(new Position(1,2))).thenReturn(false);

        Position position = new Position(4, 5);
        double radius = 1.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.NONE, axis);
    }

    @Test
    void horizontalCollision(){
        Mockito.when(field.isEmpty(new Position(4, 3))).thenReturn(false);

        Position position = new Position(4, 4);
        double radius = 1.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.HORIZONTAL, axis);
    }

    @Test
    void verticalCollision(){
        Mockito.when(field.isEmpty(new Position(3, 4))).thenReturn(false);

        Position position = new Position(4, 4);
        double radius = 1.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.VERTICAL, axis);
    }

    @Test
    void bothCollisions(){
        Mockito.when(field.isEmpty(new Position(4, 3))).thenReturn(false);
        Mockito.when(field.isEmpty(new Position(3, 4))).thenReturn(false);

        Position position = new Position(4, 4);
        double radius = 1.0;

        CollisionAxis axis = detector.collisionAxis(position, radius);

        Assertions.assertEquals(CollisionAxis.BOTH , axis);
    }
}

package com.aor.airhockey.controller;

import com.aor.airhockey.controller.collision.CollisionInfo;
import com.aor.airhockey.controller.collision.DiskBallCollisionDetector;
import com.aor.airhockey.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiskBallCollisionDetectorTest {
    private DiskBallCollisionDetector detector;

    @BeforeEach
    void setUp() {
        detector = new DiskBallCollisionDetector();
    }

    @Test
    void noCollisions() {
        Position position1 = new Position(5, 5);
        double radius1 = 1;
        Position position2 = new Position(2, 2);
        double radius2 = 2;

        CollisionInfo info = detector.collisionInfo(position1, radius1, position2, radius2);

        Assertions.assertNull(info);
    }

    @Test
    void horizontalCollision() {
        Position position1 = new Position(5, 5);
        double radius1 = 1;
        Position position2 = new Position(3, 5);
        double radius2 = 2;

        CollisionInfo info = detector.collisionInfo(position1, radius1, position2, radius2);

        Assertions.assertNotNull(info);
    }

    @Test
    void verticalCollision() {
        Position position1 = new Position(5, 5);
        double radius1 = 1;
        Position position2 = new Position(5, 3);
        double radius2 = 2;

        CollisionInfo info = detector.collisionInfo(position1, radius1, position2, radius2);

        Assertions.assertNotNull(info);
    }

    @Test
    void diagonalCollision() {
        Position position1 = new Position(5, 5);
        double radius1 = 1;
        Position position2 = new Position(3, 3);
        double radius2 = 2;

        CollisionInfo info = detector.collisionInfo(position1, radius1, position2, radius2);

        Assertions.assertNotNull(info);
    }
}

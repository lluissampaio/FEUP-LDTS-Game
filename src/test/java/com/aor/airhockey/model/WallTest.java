package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest {

    @Test
    public void createWall() {
        Wall wall = new Wall(10,20);
        Assertions.assertEquals(10, wall.getPosition().getX());
        Assertions.assertEquals(20, wall.getPosition().getY());
    }
}

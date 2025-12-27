package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Disk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiskTest {

    @Test
    void constructor() {
        Disk disk = new Disk(10, 20, 5, 1, -1, 1);

        Assertions.assertEquals(10, disk.getPosition().getX());
        Assertions.assertEquals(20, disk.getPosition().getY());
        Assertions.assertEquals(1, disk.getVelocity().getX());
        Assertions.assertEquals(-1, disk.getVelocity().getY());

        Assertions.assertEquals(5, disk.getRadius());
        Assertions.assertEquals(1, disk.getPlayerNumber());
    }

    @Test
    void simpleConstructor() {
        Disk disk = new Disk(30, 40, 3, 2);

        Assertions.assertEquals(30, disk.getPosition().getX());
        Assertions.assertEquals(40, disk.getPosition().getY());
        Assertions.assertEquals(3, disk.getRadius());
        Assertions.assertEquals(2, disk.getPlayerNumber());

        Assertions.assertEquals(0, disk.getVelocity().getX());
        Assertions.assertEquals(0, disk.getVelocity().getY());
    }

    @Test
    void settersAndGetters() {
        Disk disk = new Disk(0, 0, 1, 1);

        disk.setRadius(10);
        Assertions.assertEquals(10, disk.getRadius());

        disk.setPlayerNumber(2);
        Assertions.assertEquals(2, disk.getPlayerNumber());
    }
}
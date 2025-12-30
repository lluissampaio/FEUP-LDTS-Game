package com.aor.airhockey.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VelocityTest {

    @Test
    void constructor() {
        Velocity v = new Velocity(10.5, -5.0);

        Assertions.assertEquals(10.5, v.getX());
        Assertions.assertEquals(-5.0, v.getY());
    }

    @Test
    void settersandGetters() {
        Velocity v = new Velocity(0, 0);
        v.setVelX(50);
        v.setVelY(100);
        Assertions.assertEquals(50, v.getX());
        Assertions.assertEquals(100, v.getY());
    }

    @Test
    void testEquals() {
        Velocity v1 = new Velocity(10, 20);
        Velocity v2 = new Velocity(10, 20); // Igual a v1
        Velocity v3 = new Velocity(5, 5);   // Diferente

        Assertions.assertEquals(v1, v1);

        Assertions.assertEquals(v1, v2);
        Assertions.assertEquals(v2, v1);

        Assertions.assertNotEquals(v1, v3);

        Assertions.assertNotEquals(null, v1);
    }

    @Test
    void testHashCode() {
        Velocity v1 = new Velocity(30, 40);
        Velocity v2 = new Velocity(30, 40);

        Assertions.assertEquals(v1.hashCode(), v2.hashCode());
    }
}
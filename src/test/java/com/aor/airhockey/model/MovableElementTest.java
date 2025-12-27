package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.MovableElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MovableElementTest {

    @Test
    void constructorAndGetter() {
        MovableElement element = new MovableElement(10, 20, 5, -5);

        Assertions.assertEquals(10, element.getPosition().getX());
        Assertions.assertEquals(20, element.getPosition().getY());

        Assertions.assertNotNull(element.getVelocity());
        Assertions.assertEquals(5, element.getVelocity().getX());
        Assertions.assertEquals(-5, element.getVelocity().getY());
    }

    @Test
    void setVelocity() {
        MovableElement element = new MovableElement(0, 0, 0, 0);
        Velocity newVelocity = new Velocity(100, 200);

        element.setVelocity(newVelocity);

        Assertions.assertEquals(newVelocity, element.getVelocity());
        Assertions.assertEquals(100, element.getVelocity().getX());
    }
}

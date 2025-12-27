package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ElementTest {

    @Test
    void constructorAndGetter() {
        Element element = new Element(10, 20);

        Assertions.assertNotNull(element.getPosition());
        Assertions.assertEquals(10, element.getPosition().getX());
        Assertions.assertEquals(20, element.getPosition().getY());
    }

    @Test
    void setter() {
        Element element = new Element(5, 5);
        Position newPosition = new Position(10, 10);

        element.setPosition(newPosition);

        Assertions.assertEquals(newPosition, element.getPosition());
        Assertions.assertEquals(10, element.getPosition().getX());
    }
}

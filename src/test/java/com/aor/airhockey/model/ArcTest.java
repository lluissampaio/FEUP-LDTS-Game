package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Arc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArcTest {

    @Test
    void createArc() {
        Arc arc = new Arc(10, 20);
        Assertions.assertEquals(10, arc.getPosition().getX());
        Assertions.assertEquals(20, arc.getPosition().getY());
    }
}
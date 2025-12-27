package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Halfline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HalflineTest {

    @Test
    public void createHalfline() {
        Halfline halfline = new Halfline(10,20);
        Assertions.assertEquals(10, halfline.getPosition().getX());
        Assertions.assertEquals(20, halfline.getPosition().getY());
    }
}

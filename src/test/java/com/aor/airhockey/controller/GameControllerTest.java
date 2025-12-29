package com.aor.airhockey.controller;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.game.GameController;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.field.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

class GameControllerTest {

    class Stub extends GameController {
        public Stub(Field field) {
            super(field);
        }

        @Override
        public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
            //só precisamos de testar o construtor
        }
    }

    @Test
    void constructor() {
        Field field = Mockito.mock(Field.class);
        Stub controller = new Stub(field);
        Assertions.assertEquals(field, controller.getModel());
    }
}
package com.aor.airhockey.states;


import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.game.GameController;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.game.GameViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameStateTest {

    @Test
    void verify() throws Exception {
        Field model = Mockito.mock(Field.class);
        GameState state = new GameState(model);

        Viewer<Field> viewer = state.getViewer();
        Controller<Field> controller = state.getController();

        Assertions.assertEquals(model, state.getModel());
        Assertions.assertTrue(viewer instanceof GameViewer);//o viewer deve ser do tipo GameViewer
        Assertions.assertTrue(controller instanceof GameController);//o controller deve ser do tipo GameController
    }
}

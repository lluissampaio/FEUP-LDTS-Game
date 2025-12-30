package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.menu.EndController;
import com.aor.airhockey.model.menu.End;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.EndViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EndStateTest {

    @Test
    void verify() throws Exception {
        End model = Mockito.mock(End.class);
        EndState state = new EndState(model);

        Viewer<End> viewer = state.getViewer();
        Controller<End> controller = state.getController();

        Assertions.assertEquals(model, state.getModel());
        Assertions.assertTrue(viewer instanceof EndViewer);//o viewer deve ser do tipo EndViewer
        Assertions.assertTrue(controller instanceof EndController);//o controller deve ser do tipo EndController
    }
}
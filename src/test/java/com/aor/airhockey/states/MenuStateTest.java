package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.menu.MenuController;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.MenuViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MenuStateTest {

    @Test
    void verify() throws Exception {
        Menu model = Mockito.mock(Menu.class);
        MenuState state = new MenuState(model);

        Viewer<Menu> viewer = state.getViewer();
        Controller<Menu> controller = state.getController();

        Assertions.assertTrue(viewer instanceof MenuViewer);
        Assertions.assertTrue(controller instanceof MenuController);
        Assertions.assertEquals(model, state.getModel());
    }
}

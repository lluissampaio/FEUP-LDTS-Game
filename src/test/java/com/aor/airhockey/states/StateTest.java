package com.aor.airhockey.states;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.viewer.Viewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

class StateTest {
    private Controller<Object> controllerMock;
    private Viewer<Object> viewerMock;
    private GUI guiMock;
    private Game gameMock;

    class Stub extends State<Object> {

        public Stub(Object model) throws Exception {
            super(model);
        }

        @Override
        protected Viewer<Object> getViewer() {
            return viewerMock;
        }

        @Override
        protected Controller<Object> getController() {
            return controllerMock;
        }
    }

    @BeforeEach
    void setUp() {
        controllerMock = Mockito.mock(Controller.class);
        viewerMock = Mockito.mock(Viewer.class);
        guiMock = Mockito.mock(GUI.class);
        gameMock = Mockito.mock(Game.class);
    }

    @Test
    void constructor() throws Exception {
        Object model = new Object();
        Stub state = new Stub(model);
        Assertions.assertEquals(model, state.getModel());
    }

    @Test
    void step() throws Exception {
        Object model = new Object();
        Stub state = new Stub(model);
        long time = 100L;
        Set<GUI.ACTION> actions = Set.of(GUI.ACTION.ARR_UP);
        Mockito.when(guiMock.getNextActions()).thenReturn(actions);

        state.step(gameMock, guiMock, time);

        Mockito.verify(guiMock, Mockito.times(1)).getNextActions();
        Mockito.verify(controllerMock, Mockito.times(1)).step(gameMock, actions, time);
        Mockito.verify(viewerMock, Mockito.times(1)).draw(guiMock);
    }
}

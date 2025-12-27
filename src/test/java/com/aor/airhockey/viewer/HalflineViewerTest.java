package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Halfline;
import com.aor.airhockey.viewer.game.BallViewer;
import com.aor.airhockey.viewer.game.HalflineViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HalflineViewerTest {
    private HalflineViewer viewer;
    private GUI gui;
    private Halfline halfline;

    @BeforeEach
    void setUp() {
        viewer = new HalflineViewer();
        gui = Mockito.mock(GUI.class);
        halfline = Mockito.mock(Halfline.class);
    }

    @Test
    void drawHalfline() {
        Position position = new Position(10, 10);
        Mockito.when(halfline.getPosition()).thenReturn(position);
        viewer.draw(halfline, gui);
        Mockito.verify(gui, Mockito.times(1)).drawHalfline(position);
    }
}

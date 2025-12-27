package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Arc;
import com.aor.airhockey.viewer.game.ArcViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ArcViewerTest {
    private
    ArcViewer viewer;
    private GUI gui;
    private Arc arc;

    @BeforeEach
    void setUp() {
        viewer = new ArcViewer();
        gui = Mockito.mock(GUI.class);
        arc = Mockito.mock(Arc.class);
    }

    @Test
    void drawArc() {
        Position position = new Position(10, 20);
        Mockito.when(arc.getPosition()).thenReturn(position);
        viewer.draw(arc, gui);
        Mockito.verify(gui, Mockito.times(1)).drawArc(position);
    }
}

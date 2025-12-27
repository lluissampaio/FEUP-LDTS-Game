package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Wall;
import com.aor.airhockey.viewer.game.WallViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WallViewerTest {
    private WallViewer viewer;
    private GUI gui;
    private Wall wall;

    @BeforeEach
    void setUp() {
        viewer = new WallViewer();
        gui = Mockito.mock(GUI.class);
        wall = Mockito.mock(Wall.class);
    }

    @Test
    void drawWall() {
        Position position = new Position(10, 5);
        Mockito.when(wall.getPosition()).thenReturn(position);
        viewer.draw(wall, gui);
        Mockito.verify(gui, Mockito.times(1)).drawWall(position);
    }
}
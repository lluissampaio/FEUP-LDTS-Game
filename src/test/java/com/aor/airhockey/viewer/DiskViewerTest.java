package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.viewer.game.DiskViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DiskViewerTest {
    private DiskViewer viewer;
    private GUI gui;
    private Disk disk;

    @BeforeEach
    void setUp() {
        viewer = new DiskViewer();
        gui = Mockito.mock(GUI.class);
        disk = Mockito.mock(Disk.class);
    }

    @Test
    void drawDisk() {
        Position position = new Position(5, 5);
        Mockito.when(disk.getPosition()).thenReturn(position);
        Mockito.when(disk.getRadius()).thenReturn(2.0);
        Mockito.when(disk.getPlayerNumber()).thenReturn(1); //jogador 1
        viewer.draw(disk, gui);
        Mockito.verify(gui, Mockito.times(1)).drawDisk(position, 2.0, 1);
    }
}
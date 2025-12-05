package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.viewer.game.DiskViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DiskViewerTest {
    private Disk disk;
    private DiskViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        disk = new Disk(10, 10, 2, 1);
        viewer = new DiskViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(disk, gui);
        Mockito.verify(gui, Mockito.times(1)).drawDisk(disk.getPosition());
    }
}

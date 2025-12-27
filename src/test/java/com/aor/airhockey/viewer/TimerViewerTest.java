package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Timer;
import com.aor.airhockey.viewer.game.TimerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TimerViewerTest {
    private TimerViewer viewer;
    private GUI gui;
    private Timer timer;

    @BeforeEach
    void setUp() {
        viewer = new TimerViewer();
        gui = Mockito.mock(GUI.class);
        timer = Mockito.mock(Timer.class);
    }

    @Test
    void drawTimer() {
        viewer.draw(timer, gui);
        Mockito.verify(gui, Mockito.times(1)).drawTimer(timer, gui);
    }
}
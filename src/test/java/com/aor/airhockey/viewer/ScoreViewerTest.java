package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Score;
import com.aor.airhockey.viewer.game.ScoreViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ScoreViewerTest {
    private ScoreViewer viewer;
    private GUI gui;
    private Score score;

    @BeforeEach
    void setUp() {
        viewer = new ScoreViewer();
        gui = Mockito.mock(GUI.class);
        score = Mockito.mock(Score.class);
    }

    @Test
    void drawScore() {
        viewer.draw(score, gui);
        Mockito.verify(gui, Mockito.times(1)).drawScore(score, gui);
    }
}

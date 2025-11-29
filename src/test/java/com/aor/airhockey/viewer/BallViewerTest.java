package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.viewer.game.BallViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BallViewerTest {
    private Ball ball;
    private BallViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        ball = new Ball(10, 10, 2);
        viewer = new BallViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.draw(ball, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBall(ball.getPosition());
    }
}
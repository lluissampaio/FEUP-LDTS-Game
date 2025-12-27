package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.viewer.game.BallViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BallViewerTest {
    private BallViewer viewer;
    private GUI gui;
    private Ball ball;

    @BeforeEach
    void setUp() {
        viewer = new BallViewer();
        gui = Mockito.mock(GUI.class);
        ball = Mockito.mock(Ball.class);
    }

    @Test
    void drawBall() {
        Position position = new Position(10, 10);
        Mockito.when(ball.getPosition()).thenReturn(position);
        Mockito.when(ball.getRadius()).thenReturn(2.0);
        viewer.draw(ball, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBall(position, 2.0);
    }
}
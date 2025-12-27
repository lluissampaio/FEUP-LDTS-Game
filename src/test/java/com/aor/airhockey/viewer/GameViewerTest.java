package com.aor.airhockey.viewer;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.*;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.viewer.game.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

class GameViewerTest {
    private GameViewer viewer;
    private GUI gui;
    private Field field;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(Field.class);
        gui = Mockito.mock(GUI.class);
        viewer = new GameViewer(field);
    }

    @Test
    void drawElements() {
        // WALLS
        Wall wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(field.getWalls()).thenReturn(List.of(wall));

        // HALFLINES
        Halfline halfline = Mockito.mock(Halfline.class);
        Mockito.when(halfline.getPosition()).thenReturn(new Position(2, 2));
        Mockito.when(field.getHalfline()).thenReturn(List.of(halfline));

        // ARCS
        Arc arc = Mockito.mock(Arc.class);
        Mockito.when(arc.getPosition()).thenReturn(new Position(3, 3));
        Mockito.when(field.getArcs()).thenReturn(List.of(arc));

        // DISK 1
        Disk disk1 = Mockito.mock(Disk.class);
        Mockito.when(disk1.getPosition()).thenReturn(new Position(4, 4));
        Mockito.when(disk1.getRadius()).thenReturn(2.0);
        Mockito.when(disk1.getPlayerNumber()).thenReturn(1);
        Mockito.when(field.getDisk1()).thenReturn(disk1);

        // DISK 2
        Disk disk2 = Mockito.mock(Disk.class);
        Mockito.when(disk2.getPosition()).thenReturn(new Position(5, 5));
        Mockito.when(disk2.getRadius()).thenReturn(2.0);
        Mockito.when(disk2.getPlayerNumber()).thenReturn(2);
        Mockito.when(field.getDisk2()).thenReturn(disk2);

        // BALL
        Ball ball = Mockito.mock(Ball.class);
        Mockito.when(ball.getPosition()).thenReturn(new Position(6, 6));
        Mockito.when(ball.getRadius()).thenReturn(1.0);
        Mockito.when(field.getBall()).thenReturn(ball);

        // SCORE
        Score score = Mockito.mock(Score.class);
        Mockito.when(field.getScore()).thenReturn(score);

        // TIMER
        Timer timer = Mockito.mock(Timer.class);
        Mockito.when(field.getTimer()).thenReturn(timer);

        viewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawWall(Mockito.any(Position.class));
        Mockito.verify(gui, Mockito.times(1)).drawHalfline(Mockito.any(Position.class));
        Mockito.verify(gui, Mockito.times(1)).drawArc(Mockito.any(Position.class));
        Mockito.verify(gui, Mockito.times(2)).drawDisk(Mockito.any(Position.class), Mockito.anyDouble(), Mockito.anyInt());// Verifica se desenhou 2 discos (Disk1 e Disk2)
        Mockito.verify(gui, Mockito.times(1)).drawBall(Mockito.any(Position.class), Mockito.anyDouble());
        Mockito.verify(gui, Mockito.times(1)).drawScore(score, gui);
        Mockito.verify(gui, Mockito.times(1)).drawTimer(timer, gui);
    }
}

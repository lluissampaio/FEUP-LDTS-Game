package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Halfline;
import com.aor.airhockey.model.game.elements.Score;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.model.game.elements.Element;
import com.aor.airhockey.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Field> {
    public GameViewer(Field field) {
        super(field);
    }

    @Override
    public void drawElements(GUI gui) {
        drawElements(gui, getModel().getWalls(), new WallViewer());
        drawElement(gui, getModel().getBall(), new BallViewer());
        drawElement(gui, getModel().getDisk1(), new DiskViewer());
        drawElement(gui, getModel().getDisk2(), new DiskViewer());
        drawElements(gui, getModel().getHalfline(), new HalflineViewer());
        drawElement(gui, getModel().getScore(), new ScoreViewer());
        drawElement(gui, getModel().getTimer(), new TimerViewer());
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}

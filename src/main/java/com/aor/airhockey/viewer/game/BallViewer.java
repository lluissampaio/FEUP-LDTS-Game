package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Ball;

public class BallViewer implements ElementViewer<Ball> {
    @Override
    public void draw(Ball ball, GUI gui) {gui.drawBall(ball.getPosition());}
}

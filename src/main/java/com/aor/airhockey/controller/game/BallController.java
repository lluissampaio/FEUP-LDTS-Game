package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.field.Field;

import java.io.IOException;

public class BallController extends GameController {
    public BallController(Field field){ super(field); }

    private void moveBall() {

        Ball ball = getModel().getBall();

        double velX = ball.getVelX();
        double velY = ball.getVelY();

        double newX = ball.getPosition().getX() + velX;
        double newY = ball.getPosition().getY() + velY;

        Position newPosition = new Position(newX, newY);

        ball.setPosition(newPosition);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        moveBall();
    }
}

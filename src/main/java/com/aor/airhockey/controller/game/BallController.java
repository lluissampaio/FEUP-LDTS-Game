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

        if (collisionWithHorWall(ball.getPosition())) {
            ball.setVelY(-ball.getVelY());
        }
        else if (collisionWithVerWall(ball.getPosition())) {
            ball.setVelX(-ball.getVelX());
        }
        double velX = ball.getVelX();
        double velY = ball.getVelY();

        double newX = ball.getPosition().getX() + velX;
        double newY = ball.getPosition().getY() + velY;

        Position newPosition = new Position(newX, newY);

        ball.setPosition(newPosition);
    }

    private boolean collisionWithHorWall(Position position) {
        Position roundDownPosition = new Position(Math.round(position.getX()), Math.round(position.getY() + 0.5));
        Position roundUpPosition = new Position(Math.round(position.getX()), Math.round(position.getY() - 0.5));
        if (getModel().isWall(roundUpPosition) || getModel().isWall(roundDownPosition)) {
            return true;
        }
        return false;
    }

    private boolean collisionWithVerWall(Position position) {
        Position roundLeftPosition = new Position(Math.round(position.getX() - 0.5), Math.round(position.getY()));
        Position roundRightPosition = new Position(Math.round(position.getX() + 0.5), Math.round(position.getY()));
        if (getModel().isWall(roundLeftPosition) || getModel().isWall(roundRightPosition)) {
            return true;
        }
        return false;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        moveBall();
    }
}

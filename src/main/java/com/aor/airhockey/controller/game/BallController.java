package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.field.Field;

import java.io.IOException;

public class BallController extends GameController {
    public BallController(Field field){ super(field); }

    public void moveBallLeft() {
        moveBall(getModel().getBall().getPosition().getLeft());
    }

    public void moveBallRight() {
        moveBall(getModel().getBall().getPosition().getRight());
    }

    public void moveBallUp() {
        moveBall(getModel().getBall().getPosition().getUp());
    }

    public void moveBallDown() {
        moveBall(getModel().getBall().getPosition().getDown());
    }

    private void moveBall(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getBall().setPosition(position);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        //to do
    }
}

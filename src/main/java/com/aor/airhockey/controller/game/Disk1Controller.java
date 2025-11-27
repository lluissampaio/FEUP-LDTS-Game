package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.field.Field;

import java.io.IOException;

public class Disk1Controller extends GameController {
    public Disk1Controller(Field field){ super(field); }

    public void moveDisk1Left() {
        moveDisk1(getModel().getDisk1().getPosition().getLeft());
    }

    public void moveDisk1Right() {
        moveDisk1(getModel().getDisk1().getPosition().getRight());
    }

    public void moveDisk1Up() {
        moveDisk1(getModel().getDisk1().getPosition().getUp());
    }

    public void moveDisk1Down() {
        moveDisk1(getModel().getDisk1().getPosition().getDown());
    }

    private void moveDisk1(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getDisk1().setPosition(position);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.ARR_N) moveDisk1Up();
        if (action == GUI.ACTION.ARR_E) moveDisk1Right();
        if (action == GUI.ACTION.ARR_S) moveDisk1Down();
        if (action == GUI.ACTION.ARR_W) moveDisk1Left();
    }
}

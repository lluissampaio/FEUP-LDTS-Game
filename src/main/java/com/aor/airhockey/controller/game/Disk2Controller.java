package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.field.Field;

import java.io.IOException;

public class Disk2Controller extends GameController {
    public Disk2Controller(Field field){ super(field); }

    public void moveDisk2Left() {
        moveDisk2(getModel().getDisk2().getPosition().getLeft());
    }

    public void moveDisk2Right() {
        moveDisk2(getModel().getDisk2().getPosition().getRight());
    }

    public void moveDisk2Up() {
        moveDisk2(getModel().getDisk2().getPosition().getUp());
    }

    public void moveDisk2Down() {
        moveDisk2(getModel().getDisk2().getPosition().getDown());
    }

    private void moveDisk2(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getDisk2().setPosition(position);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.ARR_N) moveDisk2Up();
        if (action == GUI.ACTION.ARR_E) moveDisk2Right();
        if (action == GUI.ACTION.ARR_S) moveDisk2Down();
        if (action == GUI.ACTION.ARR_W) moveDisk2Left();
        // add diagonal movement
    }
}